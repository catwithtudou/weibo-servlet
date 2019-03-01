package weibo.webcontrol;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author 郑煜
 * @Title: CheckCodeServlet
 * @ProjectName weibo
 * @Description: 动态生成验证码图片页面(不需要过滤)
 * @date 2019/2/28下午 10:15
 */

@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
    private Random random=new Random();

    private Color getRandomColor(){
        int r=random.nextInt(256);
        int g=random.nextInt(256);
        int b=random.nextInt(256);
        return new Color(r,g,b);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //内存图片对象,其中(TYPE_INT_BGR 选择图片模式选择RGB模式)
        BufferedImage image=new BufferedImage(90,30,BufferedImage.TYPE_INT_BGR);
        //得到画笔之后设置画笔颜色
        Graphics  graphics=image.getGraphics();
        graphics.setColor(Color.yellow);
        //填充矩形区域
        graphics.fillRect(0,0,90,30);
        //防止通过扫描软件识别验证码
        //加上干扰线
        for(int i=0;i<5;i++){
            //颜色随机
            graphics.setColor(getRandomColor());
            int x1=random.nextInt(90);
            int y1=random.nextInt(30);
            int x2=random.nextInt(90);
            int y2=random.nextInt(30);
            graphics.drawLine(x1,y1,x2,y2);
        }

        //拼接4个验证码,画到图片上
        char [] arrays={'A','B','C','D','E','F','G','H','I'};
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<4;i++){
            //设置字符的颜色
            int index=random.nextInt(arrays.length);
            builder.append(arrays[index]);
        }
        //将生成的验证码字符串以名字为checkCode保存在session中
        req.getSession().setAttribute("checkCode",builder.toString());
        System.out.println(builder.toString());
        System.out.println(req.getSession().getAttribute("checkCode"));

        //将4个字符画到图片上
        for(int i=0;i<builder.toString().length();i++){
            graphics.setColor(getRandomColor());
            char item=builder.toString().charAt(i);
            graphics.drawString(item+"",10+(i*20),15);
        }

        ImageIO.write(image,"png",resp.getOutputStream());
    }
}
