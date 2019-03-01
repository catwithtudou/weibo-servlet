package weibo.webcontrol;

import weibo.service.GetTextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: GetTextServlet
 * @ProjectName weibo
 * @Description: 获取微博及评论json数据页面(id[微博id])(不需要过滤)
 * @date 2019/1/3112:10
 */
public class GetTextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    //此页面是根据id来返回id=?的微博及评论json数据,因需要id参数故采用dopost方式
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //此页面需要得到id参数
        String id=req.getParameter("id");
        int pid=Integer.parseInt(id);
        //使用封装功能
        if(pid!=0){
            StringBuffer sb=new StringBuffer();
            sb.append("{\"code\":200,\"message\":\"success\",\"data\":[");
            sb.append(GetTextServiceImpl.getInstance().jsonText(pid));
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
            sb.append("]}");
            resp.getWriter().print(sb.toString());
        }else{
            resp.getWriter().print("<script>alert('请重新再试');history.back();</script>");
        }
    }
}
