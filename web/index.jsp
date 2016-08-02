<%-- 
    Document   : index
    Created on : 2016/07/27, 11:53:38
    Author     : maimaimai
--%>
<%@page import="jums.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("ud");
%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごいっぱいのゆめ</title>
    </head>
    <body>
        <h1>仮想購入サービスです。</h1><br><br>
        
        <form action="Search" method="GET">
        検索キーワード入力:<input type="text" name="query" value="">      
        <input type="submit" name="btnSubmit" value="検索">
        </form><br>
        <form action="Cart" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit4" value="マイカート"><br><br>
        </form>
        
        <%if(ud != null){%>
        <form action="Mydata" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit3" value="マイページ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br><br>
        <%}%>

        
        
        
    </body>
</html>