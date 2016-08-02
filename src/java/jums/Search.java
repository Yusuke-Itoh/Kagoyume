/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author maimaimai
 */
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            //エンコーダーでqueryの内容を変換する
            String st = request.getParameter("query");
            String query = URLEncoder.encode(st,"UTF-8");
            String url = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj0zaiZpPWE3cDBVTklhWkNLbCZzPWNvbnN1bWVyc2VjcmV0Jng9ODY-&query="+query;
            //yahooAPIに接続し検索結果をもらう
            AccessYahooAPI ay = new AccessYahooAPI();
            String str =  ay.getResult(url);
            JsonNode jn = ay.getJsonNode(str);
            int trr =jn.get("ResultSet").get("totalResultsReturned").intValue();

            ArrayList<ItemDataBeans> searchResult = new ArrayList();
            //ItemDataBeansを呼び出し数値を入れておく
            for(int i=0;i<trr;i++){
            ItemDataBeans idb = new ItemDataBeans();
            idb.setImg(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Image").get("Medium").textValue());
            idb.setName(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Name").textValue());
            idb.setPrice(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Price").get("_value").textValue());
            idb.setItemcode(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Code").textValue());
            idb.setDescript(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Description").textValue());
            idb.setRate(jn.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Review").get("Rate").textValue());
            searchResult.add(idb);
            }
            
            HttpSession hs = request.getSession();
            hs.setAttribute("searchResult", searchResult);
            request.setAttribute("query",query);
            Log.getInstance().log("検索結果ページに遷移");
            request.getRequestDispatcher("search.jsp").forward(request, response); 
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
