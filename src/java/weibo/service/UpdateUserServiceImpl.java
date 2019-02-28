package weibo.service;

import weibo.bean.Userfirst;
import weibo.bean.Usersecond;
import weibo.dao.UserIDao;
import weibo.dao.UserIDaoImpl;

/**
 * @author 郑煜
 * @Title: UpdateUserServiceImpl
 * @ProjectName weibo
 * @Description: 实现更新用户数据功能
 * @date 2019/2/117:11
 */
public class UpdateUserServiceImpl implements UpdateUserService{
    //单例模式
    private static UpdateUserService instance=null;
    /**
     　　* @Description: 得到SendTextService的单例
     　　*/
    public static UpdateUserService getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (UpdateUserService.class){
                if(instance==null){
                    instance=new UpdateUserServiceImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public boolean updateUserPiece(String word,String userid) {
        //检查用户id是否存在后再更新数据
        Userfirst userfirst=new Userfirst();
        userfirst.setUserid(userid);
        boolean flag= UserIDaoImpl.getInstance().findUserId(userfirst);
        if(flag){
            if(UserIDaoImpl.getInstance().updateUserPiece(word,userid)) {
                System.out.println(userid + "更新数据成功!!!!");
            }
        }else{
            System.out.println("更新用户数据失败!!!!");
            return false;
        }
        return flag;
    }

    @Override
    public boolean addLikepeople(Usersecond usersecond) {
        //检查用户id是否存在再添加关注人和被关注人
        Userfirst u1=new Userfirst();
        Userfirst u2=new Userfirst();
        boolean flag1=UserIDaoImpl.getInstance().findlikepeople(usersecond);
        if(flag1) {
            if (!UserIDaoImpl.getInstance().findUserId(u1) && !UserIDaoImpl.getInstance().findUserId(u2)) {
                if (UserIDaoImpl.getInstance().insertLikePeople(usersecond)) {
                    System.out.println("关注人添加成功");
                }else{
                    flag1=false;
                }
            }else{
                flag1=false;
            }
        }else{
            System.out.println("关注人添加失败,请检查");
        }
        return flag1;
    }

    @Override
    public boolean updateUser(Userfirst userfirst) {
        boolean flag=UserIDaoImpl.getInstance().updateUserInformation(userfirst);
        if(flag){
            System.out.println("用户修改个人资料成功!");
        }else{
            System.out.println("用户修改个人资料失败!");
        }
        return flag;
    }

    @Override
    public boolean updatephoto(String path, String userid) {
        boolean flag=UserIDaoImpl.getInstance().updatephoto(path,userid);
        if(flag){
            System.out.println("用户上传头像成功!");
        }else{
            System.out.println("用户上传头像失败!");
        }
        return flag;
    }
}
