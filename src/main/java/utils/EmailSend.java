package utils;

import java.util.Properties;
import java.util.Random;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {

    private static String fromEmail = "3191420468@qq.com";  // 发件人账号
    private static String fromEmailPw = "baxnlahmnlokdfje";  // 发件人密码
    private static String myEmailSMTPHost = "smtp.qq.com";  // 发件邮箱服务器
    private static Properties props;  // 用于参数配置
    private static Session session;  // 用于创建会话对象
    private String vCode;
    public static EmailSend instance = new EmailSend();

    private EmailSend() {
        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");  // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);  // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");  // 需要请求认证
        session = Session.getInstance(props);
        //session.setDebug(true);  // 设置为debug模式, 可以查看详细的发送 log
    }

    /*
     * 构建邮件内容
     * @param 收件人
     * @return 发送邮件内容
     */
    public MimeMessage createMailContent(String toEmail) throws Exception, MessagingException {
        MimeMessage message = new MimeMessage(session);
        // 发件人
        message.setFrom(new InternetAddress(fromEmail, "验证码发送系统", "UTF-8"));
        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
        // 邮件主题
        message.setSubject("验证码", "UTF-8");
        // 邮件正文
        vCode = getCode();
        message.setContent("您好，您的验证码是："+vCode+"。", "text/html;charset=UTF-8");
        // 设置发件时间
        message.setSentDate(new Date());
        // 保存设置
        message.saveChanges();
        return message;
    }

    /*
     * 发送邮件
     * @param 收件人
     */
    public void sendEmail(String toEmail) throws Exception {
        Transport transport = session.getTransport();
        transport.connect(fromEmail, fromEmailPw);
        MimeMessage message = createMailContent(toEmail);  // 邮件内容
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("验证码发送成功！");
        // 关闭连接
        transport.close();
    }

    //生成验证码
    public static String getCode() {
        char[] arr = new char[26 + 26];
        int index = 0;
        for (int i = 97; i <= 122; i++) { //小写字母
            arr[index] = (char) i;
            index++;
        }
        for (int i = 65; i <= 90; i++) { //大写字母
            arr[index] = (char) i;
            index++;
        }
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = r.nextInt(arr.length);
            char c = arr[randomIndex];
            result += c;
        }
        int number = r.nextInt(10);
        return result + number;
    }

    public String getVCode() {
        return vCode;
    }

}
