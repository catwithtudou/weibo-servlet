package weibo.webcontrol;

import org.json.JSONArray;
import weibo.service.GetTextServiceImpl;
import weibo.service.GetUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 郑煜
 * @Title: GetOwnTextServlet
 * @ProjectName weibo
 * @Description: 得到本人的微博json数据页面(需要过滤)(username[有Session得到])
 * @date 2019/2/24下午 05:18
 */
public class GetOwnTextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //根据session对象获得本人userid,username
        HttpSession session=req.getSession(true);
        String username= (String) session.getAttribute("username");
        //得到自己的微博id集合
        List<Integer> ids= GetUserServiceImpl.getInstance().getUsertextid(username);
        //根据时间排序
        ids= GetTextServiceImpl.getInstance().sortedid(ids);
        StringBuffer sb=new StringBuffer();
        sb.append("{\"code\":200,\"message\":\"success\",\"data\":[");
        for(int i:ids){
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
        doGet(req, resp);
    }
}
