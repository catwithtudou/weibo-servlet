package weibo.webcontrol;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: LogoutServlet
 * @ProjectName weibo
 * @Description:注销页面(不需要过滤)
 * @date 2019/1/3018:18
 */


public class LogoutServlet extends HttpServlet {
    //不需要传输数据所以主要采用doget方式
    /**
    　　* @Description: 注销用户,关闭session对象
    　　*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //关闭session对象
        HttpSession session=req.getSession(false);
        String username=(String)session.getAttribute("userid");
        session.invalidate();
        //注销成功
        System.out.println("注销成功"+username);
        resp.getWriter().print("<script>alert('"+" 用户:"+username+" 注销成功');</script>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
