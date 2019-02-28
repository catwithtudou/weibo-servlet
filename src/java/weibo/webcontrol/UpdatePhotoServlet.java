package weibo.webcontrol;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import weibo.service.UpdateUserServiceImpl;
import weibo.utils.Gettimeutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 郑煜
 * @Title: UpdatePhotoServlet
 * @ProjectName weibo
 * @Description:上传头像页面(需要过滤)
 * @date 2019/2/18上午 11:19
 */

public class UpdatePhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        if(ServletFileUpload.isMultipartContent(req)){
            try{
                DiskFileItemFactory factory=new DiskFileItemFactory();

                ServletFileUpload sfu=new ServletFileUpload(factory);
                sfu.setSizeMax(10*1024*1024);

                sfu.setHeaderEncoding("utf-8");

                @SuppressWarnings("unchecked")
                List<FileItem> fileItemList=sfu.parseRequest(req);
                Iterator<FileItem> fileItems=fileItemList.iterator();

                while (fileItems.hasNext()){
                    FileItem fileItem=fileItems.next();
                    if(fileItem.isFormField()){
                        String name=fileItem.getFieldName();
                        String value = fileItem.getString("utf-8");
                        System.out.println(name + " = " + value);
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
                            String userid= (String) session.getAttribute("userid");
                            session.setAttribute("image_path", "image/" + newfilename);
                            UpdateUserServiceImpl.getInstance().updatephoto("image/"+newfilename,userid);
                            String url=req.getContextPath();
                            resp.getWriter().print("上传成功!!!");
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

