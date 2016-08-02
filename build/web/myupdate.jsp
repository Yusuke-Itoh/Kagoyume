<%-- 
    Document   : myupdate
    Created on : 2016/07/29, 17:30:27
    Author     : maimaimai
--%>
<%@page import="jums.UserData"%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = (UserData)session.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー情報更新ページ</title>
        <style>
                    label,input 
            {
                display: block;
                width: 150px;
                float: left;
                margin-bottom: 10px;
            }
            label {
                text-align: right;
                padding-right: 15px;
            }
            br { clear: left; }
            #btnSubmit{
                margin-left: 170px;
            }
            #div{
                margin-left: 220px;
            }
            </style>
    </head>
    <body>
        <form action="MyUpdateResult" method="POST">
            <label for="name">名前：</label>
        <input type="text" name="name" value="<%= ud.getName()%>">
        <br><br>
        <label for="password">パスワード：</label>
        <input type="text" name="password" value="<%= ud.getPassword()%>">
        <br><br>
        <label for="mailaddress">メールアドレス：</label>
        <input type="text" name="mailaddress" value="<%= ud.getMailaddress()%>">
        <br><br>
        <label for="address">住所：</label>
        <input type="text" name="address" value="<%= ud.getAddress()%>">
        <br><br>
         <input id="btnSubmit" type="submit" name="btnSubmit" value="送信">
         <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
                <br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <%=jh.home()%>
    </body>
</html>
