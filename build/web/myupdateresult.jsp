<%-- 
    Document   : myupdateresult
    Created on : 2016/07/29, 18:13:17
    Author     : maimaimai
--%>
<%@page import="jums.UserData"%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("ud");
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新完了ページ</title>
    </head>
    <body>
        <form action="MyUpdateResult" method="POST">
            <label for="name">名前：</label><%= ud.getName()%>
        <br>
        <label for="password">パスワード：</label><%= ud.getPassword()%>
        <br>
        <label for="mailaddress">メールアドレス：</label><%= ud.getMailaddress()%>
        <br>
        <label for="address">住所：</label><%= ud.getAddress()%>
        <br>
        以上の内容で更新しました。
        <br>
        <%=jh.home()%>
        </form>
        
    </body>
</html>
