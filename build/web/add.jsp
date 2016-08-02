<%-- 
    Document   : add
    Created on : 2016/07/26, 11:44:01
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
        <title>商品追加ページ</title>
    </head>
    <body>
        カートに追加しました。<br><br>
        <a href="Cart">カートへ</a><br><br>
        <a href="javascript:history.back();">戻る</a>
        <br><br>
        <%=jh.home()%>
</html>
