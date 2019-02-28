package weibo.service;

import weibo.bean.Userfirst;
import weibo.dao.UserIDao;
import weibo.dao.UserIDaoImpl;

/**
 * @author 郑煜
 * @Title: LoginServiceImpl
 * @ProjectName weibo
 * @Description: 实现登录功能
 * @date 2019/1/2117:39
 */
public class LoginServiceImpl implements LoginService {

    //单例模式
    private static LoginService instance=null;
    /**
     　　* @Description: 得到LoginService的单例
     　　*/
    public static LoginService getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (LoginService.class){
                if(instance==null){
                    instance=new LoginServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean loginUser(Userfirst userfirst) {
        boolean flag=false;
        if(UserIDaoImpl.getInstance().findUser(userfirst)){
            //登录成功
            System.out.println("登陆成功");
            flag=true;
        }
        return  flag;
    }

    @Override
    public Userfirst getUser(String userid){
        Userfirst userfirst= UserIDaoImpl.getInstance().getUserInformation(userid);
        if(userfirst==null){
            System.out.println("此用户没有信息");
        }
       return userfirst;
    }
}
