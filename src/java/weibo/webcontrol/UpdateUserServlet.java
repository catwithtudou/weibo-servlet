package weibo.webcontrol;

import weibo.bean.Userfirst;
import weibo.dao.UserIDao;
import weibo.service.LoginServiceImpl;
import weibo.service.UpdateUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: UpdateUserServlet
 * @ProjectName weibo
 * @Description: 修改个人资料页面(需要过滤)
 * @date 2019/2/17下午 07:36
 */
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //根据session对象获得userid
        HttpSession session=req.getSession(false);
        String userid= (String) session.getAttribute("userid");
        //根据userid得到用户的原始信息
        Userfirst oriuser= LoginServiceImpl.getInstance().getUser(userid);
        //若需要修改则输入相关参数且存入oriuser
        String reusername=req.getParameter("reusername");
        String birthday=req.getParameter("birthday");
        String sex=req.getParameter("sex");
        String address=req.getParameter("address");
        String profession=req.getParameter("profession");
        String introduce=req.getParameter("introduce");
        if(reusername!=null) {
            oriuser.setUsername(reusername);
        }
        if(birthday!=null){
            oriuser.setBirthday(birthday);
        }
        if(sex!=null){
            oriuser.setSex(sex);
        }
        if(address!=null){
            oriuser.setAddress(address);
        }
        if(profession!=null){
            oriuser.setProfession(profession);
        }
        if(introduce!=null){
            oriuser.setIntroduce(introduce);
        }
        //将修改后的user存入数据库
        boolean flag= UpdateUserServiceImpl.getInstance().updateUser(oriuser);
        if(flag){
            resp.getWriter().print("<script>alert('修改个人资料成功');</script>alert");
        }else{
            resp.getWriter().print("<script>alert('修改个人资料失败');</script>alert");
        }
    }
}
