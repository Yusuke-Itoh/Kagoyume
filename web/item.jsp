<%--
    Document   : item
    Created on : 2016/07/26, 11:43:52
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.ItemDataBeans"%>
<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ItemDataBeans idb = (ItemDataBeans)session.getAttribute("idb");
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
        <tr>
            <td><img src="<%=idb.getImg()%>" alt=""></td>
            <td>商品名：<%=idb.getName()%><br>
                値段：<%=idb.getPrice()%>円<br>
                レビュー点数：<%=idb.getRate()%><br><br>
                商品説明：<%=idb.getDescript()%><br>
            </td>
        </tr>    
    </table>                    
        <form action="Add" method="POST">
        <input type="submit" name="btnSubmit" value="カートに入れる">
        </form>
        <br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <br><br>
        <%=jh.home()%>
</body>
</html>