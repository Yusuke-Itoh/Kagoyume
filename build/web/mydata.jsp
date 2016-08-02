<%-- 
    Document   : mydata
    Created on : 2016/07/29, 12:44:20
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
        <title>マイデータ</title>
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
        <%if(ud != null){%>
            ユーザー名：<%=ud.getName()%><br />
            パスワード：<%=ud.getPassword()%><br />
            メールアドレス：<%=ud.getMailaddress()%><br />
            住所：<%=ud.getAddress()%><br />
            総購入金額：<%=ud.getTotal()%><br />
            登録日時：<%=ud.getNewDate()%><br />
        <br>
        <form action="MyHistry" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="購入履歴へ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form><br>
        <form action="MyUpdate" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="更新画面へ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form><br>
        <form action="MyDelete" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="削除画面へ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form><br>
        <div id="div"><%=jh.home()%></div>
        <%}%>
        
    </body>
</html>
