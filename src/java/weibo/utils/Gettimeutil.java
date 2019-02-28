package weibo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郑煜
 * @Title: Gettimeutil
 * @ProjectName weibo
 * @Description: 获取当前时间
 * @date 2019/1/2816:10
 */
public class Gettimeutil {
    public static String getcurrentTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        return time;
    }
}
