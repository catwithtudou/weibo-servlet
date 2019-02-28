package weibo.webcontrol;

import weibo.bean.Userfirst;
import weibo.service.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 郑煜
 * @Title: UserPhotoServlet
 * @ProjectName weibo
 * @Description: 得到用户头像(不需要过滤)(userid[用户id])
 * @date 2019/2/19上午 10:31
 */
public class UserPhotoServlet extends HttpServlet {
    private static final  long serialVersionUID=-1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String userid=req.getParameter("userid");
        if(userid==null||userid.equals("")) {
            userid = (String) session.getAttribute("userid");
        }
        Userfirst userfirst= LoginServiceImpl.getInstance().getUser(userid);
        FileInputStream inputStream=new FileInputStream("/var/lib/tomcat8/webapps/weibo/"+userfirst.getPhoto());
        int i=inputStream.available();
        byte[] buff=new byte[i];
        inputStream.read(buff);
        inputStream.close();
        resp.setContentType("image/*");
        OutputStream outputStream=resp.getOutputStream();
        outputStream.write(buff);
        outputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
