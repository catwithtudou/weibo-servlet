package weibo.bean;

/**
 * @author 郑煜
 * @Title: Usersecond
 * @ProjectName weibo
 * @Description: 关注人和被关注人的信息
 * @date 2019/2/117:28
 */
public class Usersecond {
    private String userid;
    private String likeuserid;

    public String getUserid() {
        return userid;
    }

    public String getLikeuserid() {
        return likeuserid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setLikeuserid(String likeuserid) {
        this.likeuserid = likeuserid;
    }
}
