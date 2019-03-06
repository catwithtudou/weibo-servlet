package weibo.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: CacheFilterutil
 * @ProjectName weibo
 * @Description:控制浏览器缓存页面中的静态资源
 * @date 2019/3/6下午 04:25
 */
public class CacheFilterutil implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        //1.获取用户想访问的资源
        String uri=request.getRequestURI();

        //2.得到用户想访问的资源的后缀名
        String ext=uri.substring(uri.lastIndexOf(".")+1);

        //得到资源需要缓存的时间
        String time = filterConfig.getInitParameter(ext);
        if(time!=null){
            long t=Long.parseLong(time)*3600*1000;
            //设置缓存
            response.setDateHeader("expires",System.currentTimeMillis()+t);
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig=filterConfig;
    }
}
