package weibo.webcontrol;

import weibo.bean.Text;
import weibo.bean.Usersecond;
import weibo.service.GetTextServiceImpl;
import weibo.service.SendTextServiceImpl;
import weibo.service.UpdateUserService;
import weibo.service.UpdateUserServiceImpl;
import weibo.utils.Gettimeutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: SendTextServlet
 * @ProjectName weibo
 * @Description: 发布微博页面(information[发布微博的内容])(需要过滤)
 * @date 2019/1/3020:18
 */

public class SendTextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
/**
　　* @Description: 此页面为发布一条微博,因为要提交数据所以采用Post方式
　　*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //创建Text对象:id;username;photo;information;time;likes;comments;pid;List<Text> childContent;
        Text text=new Text();
        //通过Session对象得到用户的用户名和头像
        HttpSession session=req.getSession();
        String userid=(String)session.getAttribute("userid");
        String username=(String)session.getAttribute("username");
        String photo=(String)session.getAttribute("photo");
        //发布微博的内容需要用户提交
        String information=req.getParameter("information");
        //获得时间
        String times= Gettimeutil.getcurrentTime();
        text.setUsername(username);text.setPhoto(photo);text.setInformation(information);text.setTime(times);
        //使用发布微博功能并返回此微博的id
        //f发布微博后个人数据中微博数+1
        int id=0;
        id=SendTextServiceImpl.getInstance().sendText(text);
        Usersecond usersecond=new Usersecond();
        usersecond.setUserid(userid);
        //判断用户是否重复提交
        boolean b=isRepeatSubmit(req);
        if(id!=0&&!b){
            System.out.println("用户"+username+"发布了一条微博");
            //将发布的微博信息封装成json数据返回给前端
            boolean flag= (boolean) UpdateUserServiceImpl.getInstance().updateUserPiece("text",usersecond.getUserid());
            if(flag){
                System.out.println(userid+"用户更新微博数成功!!!!!");
            }
            else{
                System.out.println("用户更新微博数失败!!!!!");
            }
            //模拟网络延迟重复提交
            try{
                //当前线程睡眠3秒模拟该现象
                Thread .sleep(3*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            resp.getWriter().print("<script/>"+"alert('用户"+username+"发布了一条微博');</script>");
            req.getSession().removeAttribute("token");
        }else{
            System.out.println("请不要重复提交");
            System.out.println("用户"+username+"发布微博失败,请重新再试");
        }
    }

    /**
    　　* @Description:判断客服端提交上来的令牌和服务器端生成的令牌是否一致
    　　* @param :request
    　　* @return :true 用户重复提交了
    　　*/
    public boolean isRepeatSubmit(HttpServletRequest request){
        String client_token=request.getParameter("token");
        //1.如果用户提交的表单数据没有token,则是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token=(String) request.getSession().getAttribute("token");
        //2.如果当前用户的session不存在token(令牌),则是用户重复提交了表单
        if(server_token==null){
            return true;
        }
        //3.存储在Session中的token(令牌)与表单提交Token不同,则用户重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        return false;
    }
}
