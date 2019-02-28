package weibo.service;



import weibo.bean.Text;
import weibo.dao.TextIDao;
import weibo.dao.TextIDaoImpl;

import java.util.List;

/**
 * @author 郑煜
 * @Title: GetTextServiceImpl
 * @ProjectName weibo
 * @Description: 将微博及评论封装成json数据功能
 * @date 2019/1/3015:27
 */
public class GetTextServiceImpl implements GetTextService {
    //单例模式
    private static GetTextService instance=null;
    /**
     　　* @Description: 得到LoginService的单例
     　　*/
    public static GetTextService getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (GetTextService.class){
                if(instance==null){
                    instance=new GetTextServiceImpl();
                }
            }
        }
        return instance;
    }
    //id;username;photo;information;time;likes;comments;pid;List<Text> childContent;
    @Override
    public String jsonText(int id) {
        Text text= TextIDaoImpl.getInstance().findText(id);
        List<Text> text1=TextIDaoImpl.getInstance().findTextComment(id,0);
        StringBuffer sb=new StringBuffer();
        //sb.append("{\"status\":10000,\"data\":[");
        sb.append("{\"id\":").append(text.getId())
                .append(",\"username\":\"").append(text.getUsername())
                .append("\",\"photo\":\"").append(text.getPhoto())
                .append("\",\"information\":\"").append(text.getInformation())
                .append("\",\"time\":\"").append(text.getTime())
                .append("\",\"likes\":").append(text.getLikes())
                .append(",\"comments\":").append(text.getComments())
                .append(",\"child\":[");
        for(Text content:text1){
            sb.append("{\"idf\":").append(content.getId())
                    .append(",\"username\":\"").append(content.getUsername())
                    .append("\",\"photo\":\"").append(content.getPhoto())
                    .append("\",\"information\":\"").append(content.getInformation())
                    .append("\",\"inphoto\":\"").append(content.getInphoto())
                    .append("\",\"time\":\"").append(content.getTime())
                    .append("\",\"likes\":").append(content.getLikes())
                    .append(",\"child\":[");
            List<Text> text2=TextIDaoImpl.getInstance().findTextComment(content.getId(),1);
            if(text2.size()!=0){
                for(Text recontent:text2){
                    sb.append("{\"ids\":").append(recontent.getId())
                            .append(",\"username\":\"").append(recontent.getUsername())
                            .append("\",\"photo\":\"").append(recontent.getPhoto())
                            .append("\",\"information\":\"").append(recontent.getInformation())
                            .append("\",\"inphoto\":\"").append(recontent.getInphoto())
                            .append("\",\"time\":\"").append(recontent.getTime())
                            .append("\",\"likes\":").append(recontent.getLikes())
                            .append("},");
                }
                if (sb.charAt(sb.length() - 1) == ',') {
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
            sb.append("]},");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]}");
       // sb.append("]}");
        return sb.toString();
    }

    @Override
    public List<Integer> sortedid(List<Integer> ids) {
        List<Integer> sortedids= TextIDaoImpl.getInstance().sortidbytimes(ids);
        if(sortedids==null){
            System.out.println("排序失败!!!!!");
            return  null;
        }
        return sortedids;
    }

    public static void main(String[] args) {
        System.out.println(GetTextServiceImpl.getInstance().jsonText(1));
    }

}
