<%-- 
    Document   : buyconfirm
    Created on : 2016/07/29, 13:55:56
    Author     : maimaimai
--%>
<%@page import="jums.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<ItemDataBeans> mycart =(ArrayList)hs.getAttribute("mycart");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認ページ</title>
    </head>
    <body>
        <%for(int i=0;i<mycart.size();i++){%>
        商品名：<%=mycart.get(i).getName()%><br>
        価格：<%=mycart.get(i).getPrice()%>円<br>
        <%}%>
        総金額：<%=hs.getAttribute("total")%>円
        <br>
        発送方法：
        <form action="BuyComplete" method="POST">
        <%for(int i = 1; i<=3; i++){%>
            <input type="radio" name="type" value="<%=i%>" checked = "checked"><%=jh.exTypenum(i)%><br>
        <%}%>
        <br>
        <input id="btnSubmit" type="submit" name="btnSubmit" value="購入">
        </form><br><br>

       <%=jh.home()%>
        
    </body>
</html>
