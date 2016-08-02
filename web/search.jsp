<%-- 
    Document   : search
    Created on : 2016/07/26, 11:43:23
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.ItemDataBeans"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<ItemDataBeans> searchResult=(ArrayList<ItemDataBeans>) hs.getAttribute("searchResult");
    int i = 0;
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
        <%for(i=0;i<searchResult.size();i++){%>
        
            <tr>
                <td><img src="<%=searchResult.get(i).getImg()%>" alt=""></td>   
                <td><a href="Item?resultnum=<%=i%>">商品名：<%=searchResult.get(i).getName()%>></a><br>
                    値段：<%=searchResult.get(i).getPrice()%>円</td>
            </tr>
        <%}%>
        </table>
            検索キーワード：<%=request.getParameter("query")%>
            検索数：<%=i%>件
        <br><br>
        <%=jh.home()%>
    </body>
</html>
