/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.databind.JsonNode;
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
public class MyHistry extends HttpServlet {

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
            //ユーザーIDをトリガーに商品コードをデータベースより取得
            //その後API噛ませて商品情報を持ってくる
            
            //セッションの呼び出し
            HttpSession hs = request.getSession();
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            UserData ud = (UserData)hs.getAttribute("ud");
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO searchData = new UserDataDTO();
            ud.UD2DTOMapping(searchData);
            //user_tにアクセスしユーザーIDに紐づけされたitemcodeを取り出してくる。
            UserDataDAO dao = new UserDataDAO();
            ArrayList<UserDataDTO> itemList= dao.searchBoughtItemcode(searchData);
            

            
            AccessYahooAPI ay = new AccessYahooAPI();
            ArrayList<ItemDataBeans> boughtProduct = new ArrayList();
            
            for(int i=0;i<itemList.size();i++){          
                String itemcode = itemList.get(i).getItemcode();
                String url = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj0zaiZpPWE3cDBVTklhWkNLbCZzPWNvbnN1bWVyc2VjcmV0Jng9ODY-&itemcode=" + itemcode;
                //yahooAPIに接続し検索結果をもらう
                String str =  ay.getResult(url);
                JsonNode jn = ay.getJsonNode(str);

                ItemDataBeans idb = new ItemDataBeans();
                idb.setImg(jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").textValue());
                idb.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue());
                idb.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").textValue());
                boughtProduct.add(idb);
                
           }
            //セッションに格納
            hs.setAttribute("boughtProduct", boughtProduct);
            //ログに情報を記載
            Log.getInstance().log("購入履歴ページへ遷移");
            request.getRequestDispatcher("myhistry.jsp").forward(request, response);

        }catch(Exception e){
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
