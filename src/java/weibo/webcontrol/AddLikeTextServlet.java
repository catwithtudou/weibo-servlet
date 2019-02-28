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
 * @Title: AddLikeTextServlet
 * @ProjectName weibo
 * @Description:点赞页面(key[id(微博),idf(评论),ids(评论的评论),需要前端给],username[该点赞对象的username,需要前端给])(需要过滤)
 * @date 2019/2/115:02
 */
public class AddLikeTextServlet extends HttpServlet {
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
        String key=req.getParameter("key");
        String username=req.getParameter("username");
        String id="";
        //需要要得到点赞对象的的username
        HttpSession session=req.getSession(false);
        if(key.equals("id")){
            id=req.getParameter("id");
        }
        else if(key.equals("idf")){
            id=req.getParameter("idf");
        }
        else if(key.equals("ids")){
            id=req.getParameter("ids");
        }
        int pid=Integer.parseInt(id);
        //使用点赞功能
        boolean flag= SendTextServiceImpl.getInstance().addlikes(pid,key,username);
        if(flag){
            resp.getWriter().print("点赞成功!!!!");
        }else{
            resp.getWriter().print("点赞失败请重新再试!!!");
        }
    }
}
