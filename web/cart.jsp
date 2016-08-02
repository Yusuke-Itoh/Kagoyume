<%-- 
    Document   : cart
    Created on : 2016/07/28, 10:50:59
    Author     : maimaimai
--%>
<%@page import="jums.UserData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.ItemDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    
    ArrayList<ItemDataBeans> mycart = new ArrayList();
    if(session.getAttribute("mycart")!=null){mycart =(ArrayList)session.getAttribute("mycart");}
    UserData ud = (UserData)session.getAttribute("ud");
    int cartTotal =0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>マイカート</title>
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
        <%
            for(int i=0;i<mycart.size();i++){%>
            <tr>
                <td>
                    <img src="<%=mycart.get(i).getImg()%>" alt="">
                </td>
                <td><a href="Item?cartnum=<%=i%>">商品名：<%=mycart.get(i).getName()%><br></a>
                    価格：<%=mycart.get(i).getPrice()%>円
                </td>
                <td>
                    <form action="Cart" method="POST">
                    <input id="btnSubmit" type="submit" name="btnSubmit" value="削除">
                    <input type="hidden" value="<%=i%>" name="delete">
                    </form>
                </td>
            </tr>        
            <%cartTotal += mycart.get(i).getPrice();
            }%>
        </table>  
                <%if(mycart.size() != 0){%>
                    合計金額<%=cartTotal%>円
                    <br><br>
                    <%if(ud != null){%>                           
                        <form action="BuyConfirm">
                        <input id="btnSubmit" type="submit" name="btnSubmit" value="購入画面へ">
                        </form>          
                    <%}%>
                <%}else{%>
                カートに中身がありません。
                <%}%>
                <br><br>
       <%=jh.home()%>
    </body>
</html>
