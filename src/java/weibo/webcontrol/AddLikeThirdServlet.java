package weibo.webcontrol;

import weibo.service.SendTextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: AddLikeThirdServlet
 * @ProjectName weibo
 * @Description: 点赞评论的评论页面(需要过滤)
 * @date 2019/2/27下午 11:16
 */
public class AddLikeThirdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    //此页面是点赞功能需要前端返回数据故采用post方式且需要过滤
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //此页面需要得到id和key和该点赞对象的username;
        HttpSession session=req.getSession(false);
        String username= (String) session.getAttribute("username");
        String id=req.getParameter("id");
        int pid=Integer.parseInt(id);
        //使用点赞功能
        boolean flag= SendTextServiceImpl.getInstance().addlikes(pid,"ids",username);
        if(flag){
            resp.getWriter().print("<script>alert('点赞成功')</script>");
        }else{
            resp.getWriter().print("<script>alert('点赞失败,请重新再试');history.back();</script>");
        }
    }
}


