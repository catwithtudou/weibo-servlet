package weibo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 郑煜
 * @Title: Jdbcutil
 * @ProjectName weibo
 * @Description: 数据库工具
 * @date 2019/1/2116:59
 */
public class Jdbcutil {
    public static String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
    public static String URL="jdbc:mysql://localhost/weibo?serverTimezone=GMT";
    public static String name="root";
    public static String password="a949812478";

    //驱动
    static {
        try{
            Class.forName(DRIVER_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    　　* @Description: 连接数据库
    　　* @param :
    　　* @return :Connection
    　　*/
    public static Connection getCon(){
        Connection con=null;
        try{
            con= DriverManager.getConnection(URL,name,password);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return con;
    }
    /**
    　　* @Description: 关闭数据库
    　　* @param :
    　　* @return :
    　　*/
    public static void CloseJdbc(AutoCloseable...closeable){
        for(AutoCloseable c:closeable){
            try{
                if(c!=null){
                    c.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("关闭失败");
            }
        }
    }
}
