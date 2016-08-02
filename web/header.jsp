<%-- 
    Document   : header
    Created on : 2016/08/01, 10:48:17
    Author     : maimaimai
--%>

<%@page import="jums.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData) hs.getAttribute("ud");
%>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if(ud == null){%>
        <div style="display:inline-flex">
        <form action="Login" method="POST">
        <input type="submit" name="btnSubmit" value="ログイン">
        </form>
        </div>
        <hr>
       
        <%}else{%>
        <div style="display:inline-flex">
        こんにちは、<%=ud.getName()%>さん。ただいまログイン中です。
        <form action="Logout" method="POST">
        <input type="submit" name="btnSubmit" value="ログアウト">
        </form>
        </div>
        <hr>
        <%}%>
