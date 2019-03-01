package weibo.webcontrol;

import weibo.bean.Usersecond;
import weibo.service.UpdateUserService;
import weibo.service.UpdateUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: LikePeopleServlet
 * @ProjectName weibo
 * @Description: 关注功能页面(likeuserid[关注人id,需要前端给])(需要过滤)
 * @date 2019/2/117:18
 */
public class LikePeopleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    //此页面是关注功能用户关注数+1,关注人粉丝数+1,需得得到用户和关注人的userid故采用post方式且需要过滤
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //通过Session得到登录状态的用户id
        HttpSession session=req.getSession(false);
        String userid= (String) session.getAttribute("userid");
        //前端返回关注人的userid且需要关注人的id
        String likeuserid=req.getParameter("likeuserid");
        Usersecond usersecond=new Usersecond();
        usersecond.setUserid(userid);usersecond.setLikeuserid(likeuserid);
        //使用关注功能
        boolean flag2= UpdateUserServiceImpl.getInstance().addLikepeople(usersecond);
        if(flag2){
            if(UpdateUserServiceImpl.getInstance().updateUserPiece("likes",userid)&&UpdateUserServiceImpl.getInstance().updateUserPiece("fan",likeuserid)) {
                resp.getWriter().print("<script>alert('"+userid + " 关注 " + likeuserid + " 成功');</script>");
            }else{
                resp.getWriter().print("<script>alert('"+userid+" 关注 "+likeuserid+" 失败');</script>");
            }
        }else {
            resp.getWriter().print("<script>alert('"+userid+" 关注 "+likeuserid+" 失败');</script>");
        }
    }


}
