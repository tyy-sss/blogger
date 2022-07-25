package web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import pojo.Email;
import utils.EmailSend;

@WebServlet("/emailServlet")
public class EmailSendServlet extends HttpServlet {
    private String email;  // 获取的收件人邮箱
    private String vCode;  // 后台产生的验证码
    private String vCodeReceive;  // 接收到前端输入的验证码
    private String method;  // 要接收的方法
    private PrintWriter out;  // 输出流
    private EmailSend emailSend = EmailSend.instance;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        //语言编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        out = response.getWriter();
        // 获取来自前端的参数
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Email emailSender = JSON.parseObject(s, Email.class);
        email=emailSender.getEmail();
        vCodeReceive=emailSender.getCode();
        method = request.getParameter("method");
        HttpSession session = request.getSession(true);

        switch (method) {
            case "getVCode":
                int i=mGetVCode();
                if(i==1){
                    session.setAttribute(email, vCode);
                    System.out.println(session.getAttribute(email));
                    this.removeAttribution(session, email);
                }
                break;
            case "verify":
                /*
                 * 验证码验证
                 */
                System.out.println("验证码时效性");
                System.out.println(session.getAttribute(email)+" "+vCodeReceive);
                System.out.println(vCodeReceive.equals(session.getAttribute(email)));
                if(session.getAttribute(email)==null){
                    out.print(-1);
                }else if(vCodeReceive.equals(session.getAttribute(email))==true) {
                    out.print(1);
                }else{
                    out.print(-2);
                }
                break;
            default:
                break;
        }
        out.flush();
        out.close();
    }
    /*
     * 产生验证码，并发送邮件
     */
    private int mGetVCode() {
        if(!isEmail(email)) {  // 邮箱不正确
            out.print(-1);
            return -1;
        }
        try {
            emailSend.sendEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vCode = emailSend.getVCode();
        System.out.println("验证码为：" + vCode);
        out.print(1);
        return 1;
    }
    /*
     * 邮箱正确性检测
     * @param 邮箱字符串
     * @return true/false
     */
    private boolean isEmail(String email) {
        if(email.length() == 0 || email == null) {
            return false;
        }
        // 正则表达式验证邮箱
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        return pattern.matcher(email).matches();
    }
    /*
    *设置session的时间
     */
    private void removeAttribution(final HttpSession session, final String attrName) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                session.removeAttribute(attrName);
                timer.cancel();
            }
        },  5* 60 * 1000);
    }

}
