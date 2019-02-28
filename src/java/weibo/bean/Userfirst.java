package weibo.bean;

/**
 * @author 郑煜
 * @Title: Userfirst
 * @ProjectName weibo
 * @Description: 注册页面的用户信息
 * @date 2019/1/2116:51
 */
public class Userfirst {
    /*
   CREATE TABLE `userfirst` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `birthday` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `profession` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

    private String userid;
    private String password;
    private String photo;
    private String birthday;
    private String username;
    private String sex;
    private String address;
    private String profession;
    private String times;
    private String introduce;
    private int    like;
    private int    fan;
    private int    text;

    public void setLike(int like) {
        this.like = like;
    }

    public void setText(int text) {
        this.text = text;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getProfession() {
        return profession;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getUserid() {
        return userid;
    }

    public String getPhoto() {
        return photo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getTimes() {
        return times;
    }

    public int getLike() {
        return like;
    }

    public int getFan() {
        return fan;
    }

    public int getText() {
        return text;
    }

}

