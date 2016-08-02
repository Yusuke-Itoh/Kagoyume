/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Item extends HttpServlet {

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
            
            //買い物かごから来た場合の処理
            HttpSession hs = request.getSession();
            ItemDataBeans idb= new ItemDataBeans();
            if(request.getParameter("cartnum") ==null){
                //検索番号の取得
                int resultnum =Integer.parseInt(request.getParameter("resultnum"));
                ArrayList<ItemDataBeans> searchResult=(ArrayList<ItemDataBeans>) hs.getAttribute("searchResult");
                idb.setImg(searchResult.get(resultnum).getImg());
                idb.setName(searchResult.get(resultnum).getName());
                idb.setPrice(String.valueOf(searchResult.get(resultnum).getPrice()));
                idb.setDescript(searchResult.get(resultnum).getDescript());
                idb.setRate(String.valueOf(searchResult.get(resultnum).getRate()));
                idb.setItemcode(searchResult.get(resultnum).getItemcode());

            }else{
                ArrayList<ItemDataBeans> cartList = (ArrayList) hs.getAttribute("mycart");
                idb = cartList.get(Integer.parseInt(request.getParameter("cartnum")));          
            }
            //セッションに保存
            hs.setAttribute("idb",idb);
            //ログに情報を記録
            Log.getInstance().log("商品詳細ページに遷移");            
            
            request.getRequestDispatcher("item.jsp").forward(request, response);

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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
