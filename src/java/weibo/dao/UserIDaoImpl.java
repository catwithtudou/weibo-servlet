package weibo.dao;

import weibo.bean.Userfirst;
import weibo.bean.Usersecond;
import weibo.utils.Jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郑煜
 * @Title: UserIDaoImpl
 * @ProjectName weibo
 * @Description: 实现UserIDao功能
 * @date 2019/1/2116:55
 */
public class UserIDaoImpl  implements UserIDao {

    //单例模式
    private static UserIDao instance=null;
    /**
    　　* @Description: 得到UserIDao的单例
    　　*/
    public static UserIDao getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (UserIDao.class){
                if(instance==null){
                    instance=new UserIDaoImpl();
                }
            }
        }
        return instance;
    }

    public Connection connection;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;


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

    @Override
    public boolean addUser(Userfirst user) {
        String sql="insert into userfirst(userid,password,birthday,username,sex,address,profession,times) values(?,?,?,?,?,?,?,?)";
        boolean flag=true;
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUserid());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getBirthday());
            preparedStatement.setString(4,user.getUsername());
            preparedStatement.setString(5,user.getSex());
            preparedStatement.setString(6,user.getAddress());
            preparedStatement.setString(7,user.getProfession());
            preparedStatement.setString(8,user.getTimes());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("注册失败!!!请重新再试");
            flag=false;
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement);
        return flag;
    }


    @Override
    public boolean findUser(Userfirst user) {
       String sql="SELECT DISTINCT userid,password FROM userfirst";
       String userid=user.getUserid();
       String password=user.getPassword();
       boolean flag=false;
       try{
           if(connection==null||connection.isClosed()){
               connection= Jdbcutil.getCon();
           }
           preparedStatement=connection.prepareStatement(sql);
           resultSet=preparedStatement.executeQuery();
           while(resultSet.next()){
               String useridi=resultSet.getString("userid");
               String passwordi=resultSet.getString("password");
               if(passwordi.equals(password)&&useridi.equals(userid)){
                   flag=true;
                   break;
               }
           }
       }catch (Exception e){
           e.printStackTrace();
           System.out.println("验证用户功能失败!!!!!");
       }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
       return flag;
    }

    @Override
    public boolean findUserId(Userfirst user){
        String sql="SELECT DISTINCT userid,username FROM userfirst ";
        String userid=null;
        if(user.getUserid()!=null){
            userid=user.getUserid();
        }
        String username=null;
        if(user.getUsername()!=null){
            username=user.getUsername();
        }
        boolean flag=false;
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String useridi=resultSet.getString("userid");
                String usernamei=resultSet.getString("username");
                if(useridi==null||usernamei==null){
                    continue;
                }
                if(useridi.equals(userid)||usernamei.equals(username)){
                    flag=true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证用户id功能失败!!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return flag;

    }

    @Override
    public Userfirst getUserInformation(String userid){
        String sql="SELECT * FROM userfirst where userid=?";
        Userfirst userfirst=new Userfirst();
        try{
            if(connection==null||connection.isClosed()){
                connection= Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userid);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                //  userid;password;birthday;username;sex;address;profession;
                userfirst.setUserid(userid);
                userfirst.setUsername(resultSet.getString("username"));
                userfirst.setPhoto(resultSet.getString("photo"));
                userfirst.setBirthday(resultSet.getString("birthday"));
                userfirst.setSex(resultSet.getString("sex"));
                userfirst.setAddress(resultSet.getString("address"));
                userfirst.setProfession(resultSet.getString("profession"));
                userfirst.setTimes(resultSet.getString("times"));
                userfirst.setIntroduce(resultSet.getString("introduce"));
                userfirst.setLike(resultSet.getInt("likes"));
                userfirst.setFan(resultSet.getInt("fan"));
                userfirst.setText(resultSet.getInt("text"));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查找用户失败!!!!!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return userfirst;
    }

    @Override
    public boolean updateUserPiece(String word, String userid) {
        String sql1="select "+word+" from userfirst where userid=?";
        String sql="update userfirst set "+word+"="+"1+"+word+" where userid=?";
        boolean flag=false;
        try{
           if(connection==null||connection.isClosed()){
               connection=Jdbcutil.getCon();
           }
           preparedStatement=connection.prepareStatement(sql1);
           preparedStatement.setString(1,userid);
           resultSet=preparedStatement.executeQuery();
           while(resultSet.next()){
               int number=resultSet.getInt(word);
               if(number==0){
                   sql="update userfirst set "+word+"=1 where userid=?";
               }
           }
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1,userid);
           preparedStatement.executeUpdate();
           flag=true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("更新用户Piece失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return flag;
    }

    @Override
    public boolean insertLikePeople(Usersecond usersecond) {
        String sql="insert into usersecond(userid,likeuserid) values(?,?)";
        boolean flag=false;
        try{
            if(connection==null||connection.isClosed()){
                connection=Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,usersecond.getUserid());
            preparedStatement.setString(2,usersecond.getLikeuserid());
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("插入关注人失败!!!!");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement);
        return flag;
    }

    @Override
    public boolean findlikepeople(Usersecond usersecond) {
        String sql="select * from usersecond";
        boolean flag=true;
        String userid="";
        String likeuserid="";
        try{
            if(connection==null||connection.isClosed()){
                connection=Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                userid=resultSet.getString("userid");
                likeuserid=resultSet.getString("likeuserid");
                if(userid.equals(usersecond.getUserid())&&likeuserid.equals(usersecond.getLikeuserid())){
                    flag=false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证关注人和被关注人失败");
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
        return flag;
    }

    @Override
    public List<String> getAllLikeUserid(String userid) {
         List<String> Allid=new ArrayList<>();
         String sql="select likeuserid from usersecond where userid=?";
         try{
             if(connection==null||connection.isClosed()){
                 connection=Jdbcutil.getCon();
             }
             preparedStatement=connection.prepareStatement(sql);
             preparedStatement.setString(1,userid);
             resultSet=preparedStatement.executeQuery();
             while(resultSet.next()){
                 String likeuserid=resultSet.getString("likeuserid");
                 Allid.add(likeuserid);
             }
         }catch (Exception e){
             e.printStackTrace();
             System.out.println("获取所有关注的人失败!!!!");
         }
         Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
         return Allid;
    }

    @Override
    public boolean updateUserInformation(Userfirst userfirst) {
        boolean flag=false;
        String sql="update userfirst set username=?,birthday=?,sex=?,address=?,profession=?,introduce=? where userid=?";
        try{
            if(connection==null||connection.isClosed()){
                connection=Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userfirst.getUsername());
            preparedStatement.setString(2,userfirst.getBirthday());
            preparedStatement.setString(3,userfirst.getSex());
            preparedStatement.setString(4,userfirst.getAddress());
            preparedStatement.setString(5,userfirst.getProfession());
            preparedStatement.setString(6,userfirst.getIntroduce());
            preparedStatement.setString(7,userfirst.getUserid());
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            System.out.println("修改用户个人资料失败!!!!!");
            e.printStackTrace();
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement,resultSet);
           return flag;
    }

    @Override
    public boolean updatephoto(String path,String userid) {
        boolean flag=false;
        String sql="update userfirst set photo=? where userid=?";
        try{
            if(connection==null||connection.isClosed()){
                connection=Jdbcutil.getCon();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,path);
            preparedStatement.setString(2,userid);
            preparedStatement.executeUpdate();
            flag=true;
        }catch (Exception e){
            System.out.println("用户保存头像路径失败");
            e.printStackTrace();
        }
        Jdbcutil.CloseJdbc(connection,preparedStatement);
        return flag;

    }

    public static void main(String[] args) {
        String word="likes";
        String userid="111";
        List<String> Allid=new ArrayList<>();
        Allid=UserIDaoImpl.getInstance().getAllLikeUserid("111");
        for(String user:Allid){
            System.out.println(user);
        }

    }
}
