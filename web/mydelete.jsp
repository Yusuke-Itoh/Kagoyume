<%-- 
    Document   : mydelete
    Created on : 2016/07/29, 17:15:14
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
        <title>削除確認ページ</title>
        <style>
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
        ユーザー名：<%=ud.getName()%><br />
        パスワード：<%=ud.getPassword()%><br />
        メールアドレス：<%=ud.getMailaddress()%><br />
        住所：<%=ud.getAddress()%><br />
        総購入金額：<%=ud.getTotal()%><br />
        登録日時：<%=ud.getNewDate()%><br />
        <br>
        このユーザーデータを削除しますか？
        <form action="MyDeleteResult" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="はい">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br><br>
        <form action="Index" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="いいえ">
        </form>
        <br><br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <%=jh.home()%>
    </body>
</html>
