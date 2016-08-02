<%-- 
    Document   : registrationcomplete
    Created on : 2016/08/01, 16:59:48
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.UserData"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)request.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録結果画面</title>
    </head>
    <body>
        <h2>登録結果</h2><br>
        名前:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メールアドレス:<%= ud.getMailaddress()%><br>
        住所:<%= ud.getAddress()%><br>
        以上の内容で登録しました。<br><br>

        <h3><%=jh.home()%></h3>
    </body>
</html>