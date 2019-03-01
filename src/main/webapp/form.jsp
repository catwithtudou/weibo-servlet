<%--
  Created by IntelliJ IDEA.
  User: 郑煜
  Date: 2019/3/1
  Time: 下午 08:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>模拟发布微博表单</title>
</head>
<body>
     <form action="/people/textfirst" method="post">

         <%-- 使用隐藏域存储生成的token
            <input type="hidden" name="token" value="<%=session.getAttribute("token")%>"
         --%>

         <%--使用EL表达式取出存储在session中的token--%>
         <input type="hidden" name="token" value="${token}"/>
         内容:<input type="text" name="information">
         <input type="submit" value="提交">

     </form>
</body>
</html>
