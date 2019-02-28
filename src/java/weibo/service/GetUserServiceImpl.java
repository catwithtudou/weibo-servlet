package weibo.service;

import org.json.JSONArray;
import org.json.JSONObject;
import weibo.bean.Userfirst;
import weibo.dao.TextIDaoImpl;
import weibo.dao.UserIDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郑煜
 * @Title: GetUserServiceImpl
 * @ProjectName weibo
 * @Description: 实现将用户信息封装成Json数据
 * @date 2019/2/116:04
 */
public class GetUserServiceImpl implements GetUserService {
    //单例模式
    private static GetUserService instance=null;
    /**
     　　* @Description: 得到LoginService的单例
     　　*/
    public static GetUserService getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (GetUserService.class){
                if(instance==null){
                    instance=new GetUserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public String jsonUser(String userid) {
        Userfirst userfirst=new Userfirst();
        userfirst= UserIDaoImpl.getInstance().getUserInformation(userid);
        StringBuffer sb=new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        sb.append("{\"userid\":\"").append(userfirst.getUserid())
                .append("\",\"username\":\"").append(userfirst.getUsername())
                .append("\",\"photo\":\"").append(userfirst.getPhoto())
                .append("\",\"birthday\":\"").append(userfirst.getBirthday())
                .append("\",\"sex\":\"").append(userfirst.getSex())
                .append("\",\"address\":\"").append(userfirst.getAddress())
                .append("\",\"profession\":\"").append(userfirst.getProfession())
                .append("\",\"times\":\"").append(userfirst.getTimes())
                .append("\",\"introduce\":\"").append(userfirst.getIntroduce())
                .append("\",\"like\":").append(userfirst.getLike())
                .append(",\"fan\":").append(userfirst.getFan())
                .append(",\"text\":").append(userfirst.getText())
                .append("}]}");
        return sb.toString();
    }

    @Override
    public List<Integer> getUsertextid(String username) {
        List<Integer> ids=new ArrayList<>();
        ids= TextIDaoImpl.getInstance().findusertextid(username);
        return  ids;
    }

    @Override
    public List<String> getAlllikeusernames(String userid) {
        List<String> userids=UserIDaoImpl.getInstance().getAllLikeUserid(userid);
        List<String> usernames=new ArrayList<>();
        for(String likeuserid:userids){
             Userfirst userfirst=UserIDaoImpl.getInstance().getUserInformation(likeuserid);
             usernames.add(userfirst.getUsername());
        }
        return usernames;
    }

    public static void main(String[] args) {
        System.out.println(GetUserServiceImpl.getInstance().jsonUser("111"));
    }
}
