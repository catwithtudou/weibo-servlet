package weibo.bean;

import java.util.List;

/**
 * @author 郑煜
 * @Title: Text
 * @ProjectName weibo
 * @Description: 设计微博及其评论对象
 * @date 2019/1/2815:26
 */
public class Text {


    private int id;
    private String username;
    private String photo;
    private String information;
    private String time;
    private int likes;
    private int comments;
    private int pid;
    private String inphoto;
    private List<Text> childContent;

    public String getInphoto() {
        return inphoto;
    }

    public int getComments() {
        return comments;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public int getPid() {
        return pid;
    }

    public int getId() {
        return id;
    }

    public List<Text> getChildContent() {
        return childContent;
    }

    public String getInformation() {
        return information;
    }

    public int getLikes() {
        return likes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChildContent(List<Text> childContent) {
        this.childContent = childContent;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setInphoto(String inphoto) {
        this.inphoto = inphoto;
    }
}
