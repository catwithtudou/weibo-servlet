package weibo.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author 郑煜
 * @Title: TokenProccessorutil
 * @ProjectName weibo
 * @Description: 生成令牌防止表单重复
 * @date 2019/3/1下午 08:19
 */
public class TokenProccessorutil {

    //单例模式
    private  TokenProccessorutil(){}

    private static final  TokenProccessorutil instance=new TokenProccessorutil();

    /**
    　　* @Description:返回类的对象
    　　* @return :
    　　*/
    public static TokenProccessorutil getInstance(){
        return  instance;
    }

    /**
    　　* @Description:生成Token
    　　* @param :
    　　* @return :
    　　*/
    public String makeToken(){
        String token=(System.currentTimeMillis())+new Random().nextInt(999999999)+"";
        try{
            MessageDigest md=MessageDigest.getInstance("md5");
            byte md5[] =md.digest(token.getBytes());
            //base64编码--任意二进制编码铭文字符
            BASE64Encoder encoder=new BASE64Encoder();
            return encoder.encode(md5);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    /**
     　　* @Description:判断客服端提交上来的令牌和服务器端生成的令牌是否一致
     　　* @param :request
     　　* @return :true 用户重复提交了
     　　*/
    public boolean isRepeatSubmit(HttpServletRequest request){
        String client_token=request.getParameter("token");
        //1.如果用户提交的表单数据没有token,则是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token=(String) request.getSession().getAttribute("token");
        //2.如果当前用户的session不存在token(令牌),则是用户重复提交了表单
        if(server_token==null){
            return true;
        }
        //3.存储在Session中的token(令牌)与表单提交Token不同,则用户重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        return false;
    }

}
