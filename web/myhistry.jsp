<%-- 
    Document   : myhistry
    Created on : 2016/07/30, 9:03:59
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<ItemDataBeans> boughtProduct=(ArrayList<ItemDataBeans>) hs.getAttribute("boughtProduct");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
table {
	border-collapse: collapse;
}
td {
	border: solid 1px;
	padding: 0.5em;
}
        </style>
    </head>
    <body>
        <table>
        <%for(int i=0;i<boughtProduct.size();i++){%>
        <tr>
            <td><img src="<%=boughtProduct.get(i).getImg()%>" alt=""></td>
            <td>商品名：<%=boughtProduct.get(i).getName()%>><br>
                値段：<%=boughtProduct.get(i).getPrice()%>円
            </td>
        <%}%>
        </table>
        <br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <%=jh.home()%>
    </body>
</html>