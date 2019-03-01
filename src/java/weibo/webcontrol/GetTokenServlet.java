package weibo.webcontrol;

import weibo.utils.TokenProccessorutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: GetTokenServlet
 * @ProjectName weibo
 * @Description: 生成Token(令牌)和跳转到form.jsp页面
 * @date 2019/3/1下午 08:35
 */

@WebServlet("/people/form")
public class GetTokenServlet extends HttpServlet {
    private static final long serialVersionUID=-884689940866074733L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建令牌
        String token= TokenProccessorutil.getInstance().makeToken();
        System.out.println("生成的令牌为"+token);
        //服务器使用session保存token(令牌)
        req.getSession().setAttribute("token",token);
        //跳转到form.jsp页面
        req.getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
