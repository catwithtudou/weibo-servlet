package weibo.webcontrol;

import weibo.bean.Userfirst;
import weibo.service.RegisterService;
import weibo.service.RegisterServiceImpl;
import weibo.utils.Gettimeutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: RegisterServlet
 * @ProjectName weibo
 * @Description: 注册页面(userid[用户注册id],password[用户密码],birthday[用户生日],username[昵称],sex[性别],address[地址],profession[职业])(不需要过滤)
 * @date 2019/1/2117:59
 */


public class RegisterServlet extends HttpServlet {
    //因为是提交数据所以主要post方式
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //注册用户,获得参数
        //参数有:userid;password;birthday;username;sex;address;profession;
        String userid=req.getParameter("userid");
        String password=req.getParameter("password");
        String birthday=req.getParameter("birthday");
        String username=req.getParameter("username");
        String sex=req.getParameter("sex");
        String address=req.getParameter("address");
        String profession=req.getParameter("profession");
        Userfirst user=new Userfirst();
        user.setUserid(userid);user.setPassword(password);user.setBirthday(birthday);user.setUsername(username);user.setSex(sex);user.setAddress(address);user.setProfession(profession);user.setTimes(Gettimeutil.getcurrentTime());
        //将用户存入数据库,并判断是否注册成功
        boolean flag= RegisterServiceImpl.getInstance().registerUser(user);
        if(flag){
            resp.getWriter().print("恭喜"+username+"注册成功!!!");
        }else {
            resp.getWriter().print("注册失败,请重新再试!!!");
            //重新跳转进主页
           // resp.sendRedirect("/login");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
