package weibo.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: NoCacheFilter
 * @ProjectName weibo
 * @Description: 禁止浏览器缓存所有动态页面
 * @date 2019/3/6下午 12:50
 */
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //禁止缓存所有动态页面
        /**
        *Expires数据头：值为GMT时间值，为-1指浏览器不要缓存页面
        *Cache-Control响应头有两个常用值：
        *no-cache指浏览器不要缓存当前页面。
        *max-age:xxx指浏览器缓存页面xxx秒。
         */
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        filterChain.doFilter(request,response);
    }
}
