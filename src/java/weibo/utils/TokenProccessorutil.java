package weibo.utils;

import sun.misc.BASE64Encoder;

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

}
