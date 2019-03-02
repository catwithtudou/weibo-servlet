package weibo.dao;

import weibo.bean.Text;
import weibo.bean.Userfirst;
import weibo.utils.Jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author 郑煜
 * @Title: TextIDaoImpl
 * @ProjectName weibo
 * @Description: 实现TextIDao功能
 * @date 2019/1/2816:06
 */
public class TextIDaoImpl implements TextIDao {
    //单例模式
    private static TextIDao instance=null;
    /**
     　　* @Description: 得到TextIDao的单例
     　　*/
    public static TextIDao getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (TextIDao.class){
                if(instance==null){
                    instance=new TextIDaoImpl();
                }
            }
        }
        return instance;
    }

    public Connection connection;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;



    @Override
    public int insertText(Text text) {
        String sql="INSERT INTO textfirst(username,photo,information,times,inphoto)VALUES(?,?,?,?,?)";
        int id=0;
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=(PreparedStatement)connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,text.getUsername());
            preparedStatement.setString(2,text.getPhoto());
            preparedStatement.setString(3,text.getInformation());
            preparedStatement.setString(4,text.getTime());
            preparedStatement.setString(5,text.getInphoto());
            preparedStatement.executeUpdate();
            resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("插入一条微博失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return id;
    }

    @Override
    public Text findText(int id) {
        String sql="SELECT * FROM textfirst WHERE id=?";
        Text text=new Text();
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                text.setComments(resultSet.getInt("comment"));
                text.setUsername(resultSet.getString("username"));
                text.setPhoto(resultSet.getString("photo"));
                text.setInformation(resultSet.getString("information"));
                text.setLikes(resultSet.getInt("likes"));
                text.setTime(resultSet.getString("times"));
                text.setInphoto(resultSet.getString("inphoto"));
                text.setId(id);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查找微博功能失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return text;
    }

    @Override
    public boolean insertComment(Text text,int way) {
        String sql="";
        if(way==0) {
            sql = "INSERT INTO textsecond(username,photo,information,times,pidf,inphoto)VALUES(?,?,?,?,?,?)";
        }
        else if(way==1){
            sql="INSERT INTO textthird(username,photo,information,times,pids,inphoto)VALUES(?,?,?,?,?,?)";
        }
        boolean flag=false;
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,text.getUsername());
            preparedStatement.setString(2,text.getPhoto());
            preparedStatement.setString(3,text.getInformation());
            preparedStatement.setString(4,text.getTime());
            preparedStatement.setInt(5,text.getPid());
            preparedStatement.setString(6,text.getInphoto());
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("插入一条评论失败！！！！！！");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement);
        return flag;
    }

    @Override
    public List<Text> findTextComment(int pid,int way) {
        String sql="";
        if(way==0) {
            sql = "SELECT * FROM textsecond WHERE pidf=?";
        }else if(way==1){
            sql="SELECT * FROM textthird WHERE pids=?";
        }
        List<Text> list=new ArrayList<Text>();
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Text text=new Text();
                text.setUsername(resultSet.getString("username"));
                text.setInformation(resultSet.getString("information"));
                text.setTime(resultSet.getString("times"));
                text.setPhoto(resultSet.getString("photo"));
                text.setId(resultSet.getInt("id"));
                text.setLikes(resultSet.getInt("likes"));
                if(way==0) {
                    text.setPid(resultSet.getInt("pidf"));
                }
                else if(way==1){
                    text.setPid(resultSet.getInt("pids"));
                }
                list.add(text);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查找评论功能失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return list;
    }

    @Override
    public  boolean addlikes(int id,String way,String username){
        String sql1="";
        boolean flag=false;
        String sql="";
        String name="";
        if(way.equals("idf")){
            sql1="select likes from textsecond where username=?";
            name="textsecond";
            sql="update "+name+" set likes=likes+1 where id=?";
        }
        else if(way.equals("ids")){
            sql1="select likes from textthird where username=?";
            name="textthird";
            sql="update "+name+" set likes=likes+1 where id=?";
        }
        else if(way.equals("id")){
            sql1="select likes from textfirst where username=?";
            name="textfirst";
            sql="update "+name+" set likes=likes+1 where id=?";
        }
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int likes=resultSet.getInt("likes");
                if(likes==0){
                    sql="update "+name+" set likes=1 where id=?";
                }
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("更新点赞失败!!!!");
        }
           Jdbcutil.CloseJdbc(connection,preparedStatement);
        return flag;
    }

    @Override
    public boolean addcomments(int id){
        boolean flag=false;
        String sql1="select comment from textfirst where id=?";
        String sql="update textfirst set comment=comment+1 where id=?";
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int comment=resultSet.getInt("comment");
                if(comment==0){
                    sql="update textfirst set comment=1 where id=?";
                }
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("增加评论数失败!!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return  flag;
    }


    @Override
    public List<Integer> findusertextid(String username) {
        List<Integer> ints=new ArrayList<>();
        String sql="select id from textfirst where username=? ";
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                ints.add(resultSet.getInt("id"));
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查找用户发布微博id失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return ints;
    }

    @Override
    public List<Integer> sortidbytimes(List<Integer> ids) {
        String sql = "select * from textfirst where id=?";
        Map<Integer,String> demos=new TreeMap<>();
        List<Integer> sortid = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                connection = Jdbcutil.getCon();
            }
            preparedStatement = connection.prepareStatement(sql);
            for(int id:ids){
                preparedStatement.setInt(1,id);
                resultSet=preparedStatement.executeQuery();
                while(resultSet.next()){
                    demos.put(id,resultSet.getString("times"));
                }
            }
        }catch (Exception e){
            System.out.println("排序微博id失败!!!!!!");
            e.printStackTrace();
        }
        Map<Integer,String> result=new LinkedHashMap<>();
        Stream<Map.Entry<Integer, String>> st = demos.entrySet().stream();
        st.sorted(Comparator.comparing(e->e.getValue())).forEach(e->result.put(e.getKey(),e.getValue()));
        Iterator<Integer> id=result.keySet().iterator();
        while(id.hasNext()){
            sortid.add(id.next());
        }
        return sortid;
    }


    public static void main(String[] args) {

    }
}
