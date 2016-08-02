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
public class BuyComplete extends HttpServlet {

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
            
            /*
            *テーブルuser_tの総購入金額の更新
            *テーブルbuy_tに購入商品のデータを挿入
            *それによって購入処理が行われたことを記録する
            */
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            HttpSession hs = request.getSession();
            UserData ud =(UserData)hs.getAttribute("ud");
            //カート内の総金額をudにしまう。
            int num = Integer.parseInt(hs.getAttribute("total").toString());
            ud.setTotal(Integer.parseInt(hs.getAttribute("total").toString()));
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO userdata = new UserDataDTO();
            ud.UD2DTOMapping(userdata);
            //DBでTotalの更新
            UserDataDAO.getInstance().updateTotal(userdata);
            
            //ここまでがuser_tの更新作業
            //ここからはbuy_tの更新作業
            ud.setType(Integer.parseInt(request.getParameter("type")));
            ArrayList<ItemDataBeans> mycart =(ArrayList)hs.getAttribute("mycart");
            for(int i = 0;i<mycart.size();i++){
                ud.setItemcode(mycart.get(i).getItemcode());
                ud.UD2DTOMapping(userdata);
                UserDataDAO.getInstance().BoughtProduct(userdata);                
            }
            hs.removeAttribute("mycart");            
            Log.getInstance().log("購入完了");                        
            request.getRequestDispatcher("buycomplete.jsp").forward(request, response);

        }catch(Exception e){
        //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/error.jsp").forward(request, response);
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
