package weibo.dao;

import weibo.bean.Text;

import java.util.List;

/**
 * @author 郑煜
 * @Title: TextIDao
 * @ProjectName weibo
 * @Description: 微博及其评论的数据访问
 * @date 2019/1/2815:32
 */
public interface TextIDao {
    /**
    　　* @Description:插入一条微博
    　　* @param :Text
    　　* @return :此微博在数据库中的id
    　　*/
    int insertText(Text text);


    /**
    　　* @Description: 查找id=?的微博
    　　* @param :int id
    　　* @return :Text
    　　*/
    Text findText(int id);


    /**
    　　* @Description: 插入一条评论或者评论的评论
    　　* @param :Text,way(0是评论,1是评论的评论)
    　　* @return :boolean
    　　*/
    boolean  insertComment(Text text,int way);


    /**
    　　* @Description: 查找id=?的微博的评论或者回复评论的评论
    　　* @param :int pid,way(0是评论,1是评论的评论)
    　　* @return :List<Text>
    　　*/
    List<Text> findTextComment(int pid,int way);

    /**
    　　* @Description: 给微博或者评论点赞使likes++
    　　* @param :id,key(idf是评论,ids是评论的评论,id是微博),username
    　　* @return :boolean
    　　*/
    boolean addlikes(int id,String key,String username);

    /**
    　　* @Description: 在评论后微博的comment数++
    　　* @param :int id
    　　* @return :boolean
    　　*/
    boolean addcomments(int id);

    /**
    　　* @Description: username=?的用户的微博id集合
    　　* @param :String username
    　　* @return :List
    　　*/
    List<Integer> findusertextid(String username);

    /**
    　　* @Description: 得到id集合根据时间戳排序再返回id集合
    　　* @param :List<Integer> ids
    　　* @return :List<Integer>
    　　*/
    List<Integer> sortidbytimes(List<Integer> ids);
}
