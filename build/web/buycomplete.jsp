<%-- 
    Document   : BuyComplete
    Created on : 2016/07/29, 14:39:20
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入完了</title>
    </head>
    <body>
        購入が完了しました。
        <br><br>
       <%=jh.home()%>
    </body>
</html>

