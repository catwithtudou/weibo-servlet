package weibo.service;

import weibo.bean.Text;
import weibo.dao.TextIDao;

/**
 * @author 郑煜
 * @Title: SendTextService
 * @ProjectName weibo
 * @Description: 发表微博及评论相关功能
 * @date 2019/1/2816:47
 */
public interface SendTextService {
    /**
    　　* @Description: 发布一条微博
    　　* @param :Text
    　　* @return :返回发布此微博在数据库中的id;
    　　*/
    int sendText(Text text);


    /**
    　　* @Description:发布一条评论
    　　* @param :Text,way(0是评论,1是评论的评论)
    　　* @return :boolean
    　　*/
    boolean sendComment(Text text,int way);

    /**
    　　* @Description: 发布一条评论微博的评论数要++
    　　* @param :id
    　　* @return :boolean
    　　*/
    boolean addcomment(int id);

    /**
    　　* @Description: 点赞功能
    　　* @param :id,way(idf是评论,ids是评论的评论,id是微博),username
    　　* @return :boolean
    　　*/
    boolean addlikes(int id,String  key,String username);


}
