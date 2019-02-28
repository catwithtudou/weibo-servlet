package weibo.service;

import weibo.bean.Userfirst;

/**
 * @author 郑煜
 * @Title: RegisterService
 * @ProjectName weibo
 * @Description: 注册功能
 * @date 2019/1/2117:28
 */
public interface RegisterService {
    /**
    　　* @Description: 注册用户并把用户存入数据库,注册判断用户id是否有重复
    　　* @param :Userfirst
    　　* @return :boolean
    　　*/
    boolean registerUser (Userfirst user);

}
