package weibo.service;

import weibo.bean.Userfirst;

/**
 * @author 郑煜
 * @Title: LoginService
 * @ProjectName 登录功能
 * @Description: TODO
 * @date 2019/1/2117:37
 */
public interface LoginService {

    /**
    　　* @Description: 判断输入的用户名和密码是否正确
    　　* @param :Userfirst
    　　* @return :boolean
    　　*/
    boolean loginUser(Userfirst userfirst);

    /**
    　　* @Description: 登录成功后调取用户的基本信息
    　　* @param :userid
    　　* @return :Userfirst
    　　*/
    Userfirst getUser(String userid);
}
