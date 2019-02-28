package weibo.webcontrol;

import weibo.service.GetTextServiceImpl;
import weibo.service.GetUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郑煜
 * @Title: GetLikeuserTextServlet
 * @ProjectName weibo
 * @Description: 主页上得到关注人的微博数据页面(需要过滤)
 * @date 2019/2/24下午 04:54
 */
public class GetLikeuserTextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //根据session对象获得本人userid,username
        HttpSession session=req.getSession(false);
        String userid= (String) session.getAttribute("userid");
        //根据userid得到关注的人的username集合
        List<String> likeusernames= GetUserServiceImpl.getInstance().getAlllikeusernames(userid);
        List<Integer> alltextids=new ArrayList<>();
        //根据关注的认的usernames集合得到他们的微博id集合
        for(String likeusername:likeusernames){
            List<Integer> integers=GetUserServiceImpl.getInstance().getUsertextid(likeusername);
            for(int textid:integers){
                alltextids.add(textid);
            }
        }
        //根据时间戳排序得到关注的人的所有微博id的集合
        alltextids= GetTextServiceImpl.getInstance().sortedid(alltextids);
        StringBuffer sb=new StringBuffer();
        sb.append("{\"code\":200,\"message\":\"success\",\"data\":[");
        for(int i:alltextids){
            sb.append(GetTextServiceImpl.getInstance().jsonText(i));
            sb.append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]}");
        resp.getWriter().print(sb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
