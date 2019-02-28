package weibo.webcontrol;

import weibo.bean.Text;
import weibo.bean.Userfirst;
import weibo.service.SendTextServiceImpl;
import weibo.utils.Gettimeutil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: SendCommentServlet
 * @ProjectName weibo
 * @Description: 发布评论页面(information[发布评论的内容],id[微博的id])(需要过滤)
 * @date 2019/1/3021:51
 */
public class SendCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    //此页面是发布一条评论因为提交数据所以采用post方式
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //此页面因为需登录后进入所以可以得到用户信息
        //根据session对象得到用户信息
        HttpSession session=req.getSession();
        String information=req.getParameter("information");
        String id=req.getParameter("id");
        String username= (String) session.getAttribute("username");
        String photo=(String)session.getAttribute("photo");
        String inphoto=(String)session.getAttribute("inphoto");
        //将信息存入Text对象
        Text text=new Text();
        int pid=Integer.parseInt(id);
        text.setTime(Gettimeutil.getcurrentTime());text.setPhoto(photo);text.setInformation(information);text.setUsername(username);text.setPid(pid);text.setInphoto(inphoto);
        //使用评论功能
        //使用评论后此微博的评论数+1
        boolean flag= SendTextServiceImpl.getInstance().sendComment(text,0);
        boolean reflag=SendTextServiceImpl.getInstance().addcomment(pid);
        if(flag&&reflag){
            resp.getWriter().print("用户"+username+"发布了一条评论");
            resp.getWriter().print("id="+pid+"的微博评论数+1");
        }else{
            resp.getWriter().print("发布评论失败请重新再试");
        }


    }
}
