package weibo.webcontrol;

import weibo.bean.Userfirst;
import weibo.service.LoginService;
import weibo.service.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: LoginServlet
 * @ProjectName weibo
 * @Description: 登录页面(需要过滤)
 * @date 2019/1/2117:59
 */


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //因为登录页面主要是提交数据所以采用Post方式
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //登陆页面
        String userid=req.getParameter("userid");
        String password=req.getParameter("password");
        Userfirst userfirst=new Userfirst();
        userfirst.setUserid(userid);userfirst.setPassword(password);
        //验证该用户是否存在
        boolean flag= LoginServiceImpl.getInstance().loginUser(userfirst);
        if(flag){

            //登陆成功后创建session并调取用户信息
            HttpSession session=req.getSession();
            Userfirst userfirst1= LoginServiceImpl.getInstance().getUser(userid);
            //将用户数据保存在session中
            session.setAttribute("userid",userid);
            session.setAttribute("username",userfirst1.getUsername());
           session.setAttribute("photo",userfirst1.getPhoto());
           resp.sendRedirect("/html/weibo-mainpage.html");
            resp.getWriter().print("<script>alert('用户登陆成功');window.location='/html/weibo-mainpage.html'</script>");
        }else{
            resp.getWriter().print("<script>alert('用户名或密码错误');history.back();</script>");
           // resp.sendRedirect("/login");
        }

    }

}
