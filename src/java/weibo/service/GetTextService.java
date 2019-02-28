package weibo.service;




import weibo.bean.Text;

import java.util.List;

/**
 * @author 郑煜
 * @Title: GetTextService
 * @ProjectName weibo
 * @Description: 将微博及评论封装成json数据功能
 * @date 2019/1/2816:55
 */
public interface GetTextService {
    /**
    　　* @Description: 将id=?的微博及附带的评论封装成json数据
    　　* @param :int id
    　　* @return :json
    　　*/
    String jsonText(int id);

    /**
    　　* @Description: 将id=?的微博及评论集合根据时间排序返回
    　　* @param :List<Integer> ids</Integer>
    　　* @return :List<Integer></Integer>
    　　*/
    List<Integer> sortedid(List<Integer> ids);
}
