<%-- 
    Document   : logout
    Created on : 2016/07/29, 13:21:07
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログアウト</title>
    </head>
    <body>
        ログアウトしました
        <div id="div"><%=jh.home()%></div>
    </body>
</html>
