package weibo.service;

import weibo.bean.Userfirst;
import weibo.bean.Usersecond;

/**
 * @author 郑煜
 * @Title: UpdateUserService
 * @ProjectName weibo
 * @Description: 更新用户数据功能
 * @date 2019/2/117:09
 */
public interface UpdateUserService {
    /**
    　　* @Description: 在关注和发布微博时微博数增加功能
    　　* @param :word,userid
    　　* @return :boolean
    　　*/
    boolean updateUserPiece(String word,String userid);

    /**
    　　* @Description: 关注后用户和关注人存入数据库
    　　* @param :Usersecond
    　　* @return :boolean
    　　*/
    boolean addLikepeople(Usersecond usersecond);

    /**
    　　* @Description: 修改个人用户资料功能
    　　* @param :Userfirst
    　　* @return :boolean
    　　*/
    boolean updateUser(Userfirst userfirst);

    /**
    　　* @Description: 上传用户头像功能
    　　* @param :String path,String userid
    　　* @return :boolean
    　　*/
    boolean updatephoto(String path,String userid);
}
