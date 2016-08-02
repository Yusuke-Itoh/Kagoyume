<%-- 
    Document   : deleteduser
    Created on : 2016/08/01, 14:41:01
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除されたユーザー</title>
    </head>
    <body>
        既に削除されたユーザーです。
        <br><br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <%=jh.home()%>
    </body>
</html>

