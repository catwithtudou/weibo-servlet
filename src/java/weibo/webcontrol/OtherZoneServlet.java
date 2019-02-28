package weibo.webcontrol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: OtherZoneServlet
 * @ProjectName weibo
 * @Description: 他人主页页面(otheruserid[他人的userid,需要前端给])(不需要过滤)
 * @date 2019/2/317:15
 */
public class OtherZoneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //由前端获得他人的otheruserid
        String otheruserid=req.getParameter("otheruserid");
        //根据他人的userid获得用户资料和发布的微博
    }
}
