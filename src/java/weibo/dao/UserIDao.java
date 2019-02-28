package weibo.dao;

import weibo.bean.Userfirst;
import weibo.bean.Usersecond;

import java.util.List;

/**
 * @author 郑煜
 * @Title: UserIDao
 * @ProjectName weibo
 * @Description: 用户注册的数据访问
 * @date 2019/1/2116:47
 */
public interface UserIDao {
    /**
    　　* @Description:用户注册存入数据库
    　　* @param :Userfirst
    　　* @return :boolean
    　　*/
    boolean addUser(Userfirst user);
    /**
    　　* @Description: 验证数据库里是否有该用户
    　　* @param :Userfirst
    　　* @return :boolean
    　　*/
     boolean findUser(Userfirst user);
     /**
     　　* @Description:验证数据库是否有重复id或昵称
     　　* @param :Userfirst
     　　* @return :boolean
     　　*/
     boolean findUserId(Userfirst user);
     /**
     　　* @Description:得到该用户的用户名和头像和其他信息
     　　* @param :userid
     　　* @return :Userfirst
     　　*/
     Userfirst getUserInformation(String userid);

     /**
     　　* @Description: 更新用户的text,fan,like数据
     　　* @param :String word(text,fan,like),String userid
     　　* @return :boolean
     　　*/
     boolean updateUserPiece(String word,String userid);

     /**
     　　* @Description: 插入关注人和被关注人的信息
     　　* @param :Usersecond
     　　* @return :boolean
     　　*/
     boolean insertLikePeople(Usersecond usersecond);

     /**
     　　* @Description: 验证关注人和被关注人是否重复
     　　* @param :Usersecond
     　　* @return :boolean
     　　*/
     boolean findlikepeople(Usersecond usersecond);

     /**
     　　* @Description: 获得你所有关注的人的userid
     　　* @param :userid
     　　* @return :List<String>
     　　*/
     List<String> getAllLikeUserid(String userid);

     /**
     　　* @Description: 修改该用户的信息
     　　* @param :Userfirst userfirst(username,birthday,sex,address,profession,introduce)
     　　* @return :boolean
     　　*/
     boolean updateUserInformation(Userfirst userfirst);

     /**
     　　* @Description: 保存用户头像路径
     　　* @param :String path,String userid
     　　* @return :boolean
     　　*/
     boolean updatephoto(String path,String userid);
}
