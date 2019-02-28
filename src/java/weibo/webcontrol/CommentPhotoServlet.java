package weibo.webcontrol;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import weibo.utils.Gettimeutil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author 郑煜
 * @Title: CommentPhotoServlet
 * @ProjectName weibo
 * @Description:上传评论的图片页面(需要过滤)
 * @date 2019/2/24下午 05:59
 */
public class CommentPhotoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //设置session对象
        HttpSession session=req.getSession();
        //上传用户评论的图片
        if(ServletFileUpload.isMultipartContent(req)){
            try{
                DiskFileItemFactory factory=new DiskFileItemFactory();

                ServletFileUpload sfu=new ServletFileUpload(factory);
                sfu.setSizeMax(10*1024*1024);

                sfu.setHeaderEncoding("utf-8");

                @SuppressWarnings("unchecked")
                List<FileItem> fileItemList=sfu.parseRequest(req);
                Iterator<FileItem> fileItems=fileItemList.iterator();

                while(fileItems.hasNext()){
                    FileItem fileItem=fileItems.next();
                    if(fileItem.isFormField()){
                        String name=fileItem.getFieldName();
                        String value=fileItem.getString("utf-8");
                        System.out.println(name+"+"+value);
                    }else{
                        String filename=fileItem.getName();
                        System.out.println(filename);

                        String suffix=filename.substring(filename.lastIndexOf("."));
                        System.out.println(suffix);

                        String newfilename=filename;
                        System.out.println(newfilename);

                        File file=new File("/var/lib/tomcat8/webapps/weibo/image/"+newfilename);
                        System.out.println(file.getAbsolutePath());
                        fileItem.write(file);

                        fileItem.delete();

                        if(session!=null){
                            session.setAttribute("inphoto","image/"+newfilename);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
