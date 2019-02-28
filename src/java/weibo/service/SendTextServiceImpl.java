package weibo.service;

import weibo.bean.Text;
import weibo.dao.TextIDao;
import weibo.dao.TextIDaoImpl;

/**
 * @author 郑煜
 * @Title: SendTextServiceImpl
 * @ProjectName weibo
 * @Description: TODO
 * @date 2019/1/2816:49
 */
public class SendTextServiceImpl implements SendTextService {
    //单例模式
    private static SendTextService instance=null;
    /**
     　　* @Description: 得到SendTextService的单例
     　　*/
    public static SendTextService getInstance(){
        //双重检验锁
        if(instance==null){
            synchronized (SendTextService.class){
                if(instance==null){
                    instance=new SendTextServiceImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public int sendText(Text text) {
         int id=0;
         id=TextIDaoImpl.getInstance().insertText(text);
         if(id!=0){
             System.out.println("发布一条微博成功!!!");
         }else{
             System.out.println("发布一条微博失败!!!");
         }
         return id;
    }

    @Override
    public boolean sendComment(Text text,int way) {
        boolean flag=false;
        if(TextIDaoImpl.getInstance().insertComment(text,way)){
            flag=true;
            System.out.println("插入一条评论成功");
        }
        return flag;
    }

    @Override
    public boolean addcomment(int id) {
        boolean flag=TextIDaoImpl.getInstance().addcomments(id);
        if(flag){
            System.out.println("id="+id+"的微博评论数+1");
        }else{
            System.out.println("增加评论数失败请重新再试");
        }
        return flag;
    }

    @Override
    public boolean addlikes(int id, String key,String username) {
        boolean flag=TextIDaoImpl.getInstance().addlikes(id,key,username);
        if(flag){
            System.out.println("id="+id+"点赞数+1");
        }else{
            System.out.println("点赞失败请重新再试");
        }
        return flag;
    }

//    public static void main(String[] args) {
//        SendTextServiceImpl.getInstance().addlikes(1,"idf","2131");
//    }
}
