package weibo.webcontrol;

import weibo.service.GetUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: OwnInformationServlet
 * @ProjectName weibo
 * @Description: 个人资料页面(需要过滤)(为Upadate)
 * @date 2019/2/118:02
 */
public class OwnInformationServlet extends HttpServlet {

    //此页面是根据userid(session可得)来进入个人主页故采用get方式

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //根据session对象获得userid
        HttpSession session=req.getSession(false);
        String userid= (String) session.getAttribute("userid");
        //获得userid后调取个人用户信息
        String json= GetUserServiceImpl.getInstance().jsonUser(userid);
        if(json!=null){
            resp.getWriter().print(json);
        }else{
            resp.getWriter().print("获取信息失败请重新再试!!!!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
