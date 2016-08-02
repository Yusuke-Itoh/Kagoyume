<%-- 
    Document   : mydeleteresult
    Created on : 2016/07/29, 17:21:43
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp" flush="true" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除完了</title>
    </head>
    <body>
        削除しました。
        <br><br>
        <div id="div"><%=jh.home()%></div>
    </body>
</html>
