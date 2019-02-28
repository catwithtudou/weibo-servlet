package weibo.service;

import weibo.bean.Userfirst;

import java.util.List;

/**
 * @author 郑煜
 * @Title: GetUserService
 * @ProjectName weibo
 * @Description: 获得用户信息功能
 * @date 2019/2/115:58
 */
public interface GetUserService {
    /**
    　　* @Description: 将用户信息封装成Json数据返回给前端
    　　* @param :Userfirst
    　　* @return :String json
    　　*/
    String jsonUser(String userid);

   /**
   　　* @Description: username=?用户的微博id集合且根据时间排序
   　　* @param :username
   　　* @return :int[]
   　　*/
   List<Integer> getUsertextid(String username);

   /**
   　　* @Description:username=?用户的关注的人的username集合
   　　* @param :userid
   　　* @return :List<String>
   　　*/
   List<String> getAlllikeusernames(String userid);

}
