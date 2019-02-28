package weibo.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @author 郑煜
 * @Title: loginFilterutil
 * @ProjectName weibo
 * @Description: 登录过滤器
 * @date 2019/1/2119:23
 */

public class loginFilterutil implements Filter {
    @Override
    public void init(FilterConfig filterConfig)throws ServletException{
        System.out.println("过滤器启动");
    }
    //此页面通过登陆成功得到的session判断是否能进入其他页面
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //得到Session对象
        HttpSession session=request.getSession(false);
        //根据session对象是否为空判断是否能进入/people/* 页面
        //如果没有Session和Username,则返回登录页面/login
        String userid=(String)session.getAttribute("userid");
        if(session==null||userid==null){
            System.out.println("请登录后再试");
            response.getWriter().print("请登录后再试");
            //response.sendRedirect("/login");
        }
        else{
            //若登录成功则可以访问 /people/* 页面
            filterChain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {
    }
}
