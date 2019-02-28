package weibo.service;

import weibo.bean.Userfirst;
import weibo.dao.UserIDao;
import weibo.dao.UserIDaoImpl;

/**
 * @author 郑煜
 * @Title: RegisterServiceImpl
 * @ProjectName weibo
 * @Description: 实现注册功能
 * @date 2019/1/2117:30
 */
public class RegisterServiceImpl implements RegisterService {

        //单例模式
        private static RegisterService instance=null;
        /**
         　　* @Description: 得到RegisterService的单例
         　　*/
        public static RegisterService getInstance(){
            //双重检验锁
            if(instance==null){
                synchronized (RegisterService.class){
                    if(instance==null){
                        instance=new RegisterServiceImpl();
                    }
                }
            }
            return instance;
        }

        @Override
        public boolean registerUser(Userfirst userfirst){
            boolean flag=false;
            if(UserIDaoImpl.getInstance().findUserId(userfirst)){
                //注册失败
                System.out.println("该用户名或者账号重复,请重新再试!!!!");
            }
            else{
                if(UserIDaoImpl.getInstance().addUser(userfirst)){
                    System.out.println("注册该用户成功!!!!");
                    flag=true;
                }
            }
            return flag;
        }

}
