package weibo.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 郑煜
 * @Title: CharacterEncodingFilterutil
 * @ProjectName weibo
 * @Description: 此过滤器用来解决全站中文乱码问题
 * @date 2019/3/6下午 12:27
 */
public class CharacterEncodingFilterutil implements Filter {


    private  FilterConfig filterConfig=null;
    //设置默认的字符编码
    private String defaultCharset="UTF-8";



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws UnsupportedEncodingException, IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String charset=filterConfig.getInitParameter("charset");
        if(charset==null){
            charset=defaultCharset;
        }
        request.setCharacterEncoding(charset);
        response.setContentType(charset);
        response.setContentType("text/html;charset="+charset);

        MyCharacterEncodingRequest requestWrapper=new MyCharacterEncodingRequest(request);
        filterChain.doFilter(requestWrapper,response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig=filterConfig;
    }

    @Override
    public void destroy() {

    }
}

/**
 *1.实现与被增加对象相同的接口
 *2.定义一个变量记住被增强的对象
 *3.定义一个构造器,接收被增强对象
 *4.覆盖需要增强的方法
 *5.对于不想增强的方法,直接调用被增强对象(目标对象)的方法
 　*/
class MyCharacterEncodingRequest extends HttpServletRequestWrapper{

    private HttpServletRequest request;
    public MyCharacterEncodingRequest(HttpServletRequest request) {
        super(request);
        this.request=request;
    }
    /**
    　　* @Description: 重写getParameter方法
    　　* @param :name
    　　*/
    @Override
    public String getParameter(String name){
        try{
            //获取参数的值
            String value=this.request.getParameter(name);
            if(value==null){
                return null;
            }
            //若不是以get方式提交数据的,就直接返回获取到的值
            if(!this.request.getMethod().equalsIgnoreCase("get")){
                return value;
            }else{
                //如果是以get方式提交数据的,就对获取到的值进行转码处理
                value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
                return value;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
