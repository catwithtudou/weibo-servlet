## 项目readme

[TOC]



#### bean层

- Text(设计微博及其评论的对象)

  ```java
  private int id;
  private String username;
  private String photo;
  private String information;
  private String time;
  private int likes;
  private int comments;
  private int pid;
  private List<Text> childContent;
  private String inphoto
  ```

- Userfirtst(用户的对象)

  ```java
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
  ```

- Usersecond(关注人id和被关注人id的对象)

  ```java
  private String userid;
  private String likeuserid;
  ```



#### dao层

- TextIDao(微博及其评论的数据访问)

  ```java
  /**
  　　* @Description:插入一条微博
  　　* @param :Text
  　　* @return :此微博在数据库中的id
  　　*/
  int insertText(Text text);
  
  
  /**
  　　* @Description: 查找id=?的微博
  　　* @param :int id
  　　* @return :Text中id,username,photo,information,likes,comment,times
  　　*/
  Text findText(int id);
  
  
  /**
  　　* @Description: 插入一条评论或者评论的评论
  　　* @param :Text(username,photo,information,times,pid),way(0是评论,1是评论的评论)
  　　* @return :boolean
  　　*/
  boolean  insertComment(Text text,int way);
  
  
  /**
  　　* @Description: 查找id=?的微博的评论或者回复评论的评论
  　　* @param :int pid,way(0是评论,1是评论的评论)
  　　* @return :List<Text>
  　　*/
  List<Text> findTextComment(int pid,int way);
  
  /**
  　　* @Description: 给微博或者评论点赞使likes++
  　　* @param :id,key(idf是评论,ids是评论的评论,id是微博),username
  　　* @return :boolean
  　　*/
  boolean addlikes(int id,String key,String username);
  
  /**
  　　* @Description: 在评论后微博的comment数++
  　　* @param :int id
  　　* @return :boolean
  　　*/
  boolean addcomments(int id);
  
      /**
      　　* @Description: username=?的用户的微博id集合
      　　* @param :String username
      　　* @return :List
      　　*/
      List<Integer> findusertextid(String username);
  
      /**
      　　* @Description: 得到id集合根据时间戳排序再返回id集合
      　　* @param :List<Integer> ids
      　　* @return :List<Integer>
      　　*/
      List<Integer> sortidbytimes(List<Integer> ids);
      
  ```

- UserIDao(关于用户的数据访问)

  ```java
  /**
  　　* @Description:用户注册存入数据库
  　　* @param :Userfirst
  　　* @return :boolean(true存入)
  　　*/
  boolean addUser(Userfirst user);
  /**
  　　* @Description: 验证数据库里是否有该用户
  　　* @param :Userfirst(userid,password)
  　　* @return :boolean(true有该用户)
  　　*/
   boolean findUser(Userfirst user);
   /**
   　　* @Description:验证数据库是否有重复id或昵称
   　　* @param :Userfirst(userid,username)
   　　* @return :boolean(true有重复)
   　　*/
   boolean findUserId(Userfirst user);
   /**
   　　* @Description:得到该用户的用户名和头像和其他信息
   　　* @param :userid
   　　* @return :Userfirst全部
   　　*/
   Userfirst getUserInformation(String userid);
  
   /**
   　　* @Description: 更新用户的text(微博数),fan(粉丝数),like(关注人数)数据
   　　* @param :String word(text,fan,like),String userid
   　　* @return :boolean(true更新成功)
   　　*/
   boolean updateUserPiece(String word,String userid);
  
   /**
   　　* @Description: 插入关注人和被关注人的信息
   　　* @param :Usersecond(userid,likeuserid)
   　　* @return :boolean(true插入成功)
   　　*/
   boolean insertLikePeople(Usersecond usersecond);
  
   /**
   　　* @Description: 验证关注人和被关注人是否重复
   　　* @param :Usersecond(userid,likeuserid)
   　　* @return :boolean(true)
   　　*/
   boolean findlikepeople(Usersecond usersecond);
  
       /**
       　　* @Description: 获得你所有关注的人的userid
       　　* @param :userid
       　　* @return :List<String>
       　　*/
       List<String> getAllLikeUserid(String userid);
  
       /**
       　　* @Description: 修改该用户的信息
       　　* @param :Userfirst userfirst(username,birthday,sex,address,profession,introduce)
       　　* @return :boolean
       　　*/
       boolean updateUserInformation(Userfirst userfirst);
       /**
       　　* @Description: 保存用户头像路径
       　　* @param :String path,String userid
       　　* @return :boolean
       　　*/
       boolean updatephoto(String path,String userid);
  ```

#### service层

- GetTextService(将微博及评论封装成json数据功能)

  ```java
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
  ```

- GetUserService( 将用户信息封装成json功能;将username=?的用户发布的微博的id集合返回)

  ```java
   /**
   　　* @Description: 将用户信息封装成Json数据返回给前端
   　　* @param :Userfirst
   　　* @return :String json
   　　*/
   String jsonUser(String userid);
  
     /**
     　　* @Description: username=?用户的微博id集合且根据时间排序
     　　* @param :username
     　　* @return :int[]
     　　*/
     List<Integer> getUsertextid(String username);
  
     /**
     　　* @Description:username=?用户的关注的人的username集合
     　　* @param :userid
     　　* @return :List<String>
     　　*/
     List<String> getAlllikeusernames(String userid);
  ```

- LoginService(登录功能)

  ```java
  /**
  　　* @Description: 判断输入的用户名和密码是否正确
  　　* @param :Userfirst
  　　* @return :boolean(true正确)
  　　*/
  boolean loginUser(Userfirst userfirst);
  
  /**
  　　* @Description: 登录成功后调取用户的基本信息
  　　* @param :userid
  　　* @return :Userfirst
  　　*/
  Userfirst getUser(String userid);
  ```

- RegisterService(注册功能)

  ```java
  /**
  　　* @Description: 注册用户并把用户存入数据库,注册判断用户id是否有重复
  　　* @param :Userfirst(userid,password,birthday,username,sex,address,profession,times)
  　　* @return :boolean(true注册成功)
  　　*/
  boolean registerUser (Userfirst user);
  ```

- SendTextService(发表微博及评论相关功能)

  ```java
  /**
  　　* @Description: 发布一条微博
  　　* @param :Text
  　　* @return :返回发布此微博在数据库中的id;
  　　*/
  int sendText(Text text);
  
  
  /**
  　　* @Description:发布一条评论
  　　* @param :Text,way(0是评论,1是评论的评论)
  　　* @return :boolean(true发布成功)
  　　*/
  boolean sendComment(Text text,int way);
  
  /**
  　　* @Description: 发布一条评论微博的评论数要++
  　　* @param :id
  　　* @return :boolean(true评论数++)
  　　*/
  boolean addcomment(int id);
  
  /**
  　　* @Description: 点赞功能
  　　* @param :id,way(idf是评论,ids是评论的评论,id是微博),username
  　　* @return :boolean(true点赞成功)
  　　*/
  boolean addlikes(int id,String  key,String username);
  ```

- UpdateUserService(更新用户数据功能)

  ```java
      /**
      　　* @Description: 在关注和发布微博时微博数增加功能
      　　* @param :word,userid
      　　* @return :boolean
      　　*/
      boolean updateUserPiece(String word,String userid);
  
      /**
      　　* @Description: 关注后用户和关注人存入数据库
      　　* @param :Usersecond
      　　* @return :boolean
      　　*/
      boolean addLikepeople(Usersecond usersecond);
  
      /**
      　　* @Description: 修改个人用户资料功能
      　　* @param :Userfirst
      　　* @return :boolean
      　　*/
      boolean updateUser(Userfirst userfirst);
      /**
      　　* @Description: 上传用户头像功能
      　　* @param :String path,String userid
      　　* @return :boolean
      　　*/
      boolean updatephoto(String path,String userid);
  ```

#### utils层

- Gettimeutil:得到当前时间戳工具
- Jdbcutil:数据库驱动和关闭工具
- LoginFilterutil:登录过滤器
- TokenProccessorutil:生成令牌工具

#### webcontrol层

- **LoginServlet:登录页面(需要过滤)  /login**

  1.userid

  2.password

  且登录成功后session中存入用户:userid,username,photo

- **LogoutServlet:注销页面(不需要过滤) /logout**

- **RegisterServlet:注册页面(不需要过滤) /register**

  1.userid[用户注册id],

  2.password[用户密码],

  3.birthday[用户生日],

  4.username[昵称],

  5.sex[性别],

  6.address[地址],

  7.profession[职业]

- **SendTextServlet:发布微博页面(需要过滤) /people/textfirst**

  1.information[发布微博的内容]

  (2.userid,username,photo[均由session得到] 3.times[由Gettimeutil得到])

- **SendCommentServlet:发布评论页面(需要过滤) /people/textsecond**

  1.information[发布评论的内容]

  (2.username,photo[均由session得到] 3.times[由Gettimeutil得到])

  (4.id[微博的id]-------从微博的json数据中由前端给出)

- **SendRecommentServlet:发布评论的评论页面(需要过滤) /people/textthird**

  1.information[发布评论的评论的内容]

  (2.username,photo[均由session得到] 3.times[由Gettimeutil得到])

  (4.idf[微博的id]-------从评论的json数据中由前端给出)

- **AddLikeTextServlet:点赞页面(需要过滤) /people/addliketext**

  (1.key[id(微博),idf(评论),ids(评论的评论),需要前端给])

  (2.username[该点赞对象的username,需要前端给])

- **LikePeopleServlet:关注他人页面(需要过滤) /people/likepeople**

  (1.likeuserid[关注人id,需要前端给])

  (2.userid[由session得到])

- **GetTextServlet:获取id=?微博及评论json数据页面(不需要过滤) /gettext**

  1.id[微博id]

- **OwninformationServlet:个人资料页面(需要过滤) /people/owninformation**

- OtherZoneServlet:他人主页页面(不需要过滤)

  (1.otheruserid[他人的userid,需要前端给])

- **GetOwnTextServlet:得到自己微博json数据页面(需要过滤) /people/owntext**

  (1.username[由session得到])

- **UpdateUserServlet:修改个人资料页面(需要过滤)   /people/updateuser**

  (1.userid[由session得到])

  2.username

  3.birthday

  4.introduce

  5.sex

  6.address

  7.profession

  若未修改则是null值

- **GetLikeuserTextServlet:得到关注人的微博json数据页面(需要过滤) /people/main**

(1.userid[由session得到])

- **UpdatePhotoServlet:上传用户头像页面(需要过滤) /people/updatephoto**
- **UserPhotoServlet:得到用户头像页面(不需要过滤) /photo**
- **CommentPhotoServlet:上传评论的图片页面(需要过滤)  /people/textphoto**
- **AddLikeFirstTextServlet:点赞微博页面(需要过滤) /people/addlikefirst**

- **AddLikeSecondTextServlet:点赞评论页面(需要过滤) /people/addlikesecond**

- **AddLikeThirdTextServlet:点赞评论的评论页面(需要过滤) /people/addlikethird**

- **GetTokenServlet:得到Token令牌(防止用户重复提交数据)的页面(需要过滤) /people/form**

- **CheckCodeServlet:得到验证码图片及session的页面(不需要过滤) /checkCode**

  

  