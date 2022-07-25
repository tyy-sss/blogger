package web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
登录验证的过滤器
 */
@WebFilter("/login.html")
public class LoginFiler implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        //判断有关的资源
//        String[] urls={"/login/html","/register.html"}
        System.out.println("FilterDemo");
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user!=null){
            //登录放行
            filterChain.doFilter(servletRequest, servletResponse) ;
        }else{
            //没有登录
            req.setAttribute("login_msg","您没有登录");
            req.getRequestDispatcher("/login.html").forward(req,servletResponse );
            req.getRequestDispatcher("/register.html");
            req.getRequestDispatcher("/forgetPassword.html");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
