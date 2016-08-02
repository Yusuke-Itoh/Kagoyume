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
public class LoginResult extends HttpServlet {

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {//フォームからの入力を取得して、JavaBeansに格納
            

            
                HttpSession hs = request.getSession();
                request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
                UserData ud= new UserData();
                
            //アクセスルートチェック
                String accesschk = request.getParameter("ac");
                if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                    throw new Exception("不正なアクセスです");
                }
                
            //フォームからユーザー名とパスワードを取得する
                ud.setName(request.getParameter("name"));
                ud.setPassword(request.getParameter("password"));           

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                UserDataDTO searchData = new UserDataDTO();
                ud.UD2DTOMapping(searchData);

            //DBへの検索処理
                UserDataDAO dao = new UserDataDAO();
                ArrayList<UserDataDTO> udList= dao.forLogin(searchData);
                
                //ユーザー名、パスワードが正しければDBから要素がとれている。
                if(udList.size() == 0){
                    //ログに情報を記載
                    Log.getInstance().log("不正なログイン");
                    request.getRequestDispatcher("/loginerror.jsp").forward(request, response);  
                }else{
                    //要素をUserDataに格納    
                    for(int i=0;i<udList.size();i++){
                        UserDataDTO udd = udList.get(i);
                        ud.DTO2UDMapping(udd);

                            //削除フラグのチェック
                            if(ud.getDeleteFlg() == 0){
                                hs.setAttribute("ud", ud);
                            //ログに情報を記載
                            Log.getInstance().log("ログイン成功");    
                            //一つ前の操作をしていたページにリダイレクト
                                response.sendRedirect(String.valueOf(hs.getAttribute("page")));
                            }else{    
                                //ログに情報を記載
                                Log.getInstance().log("削除されたアカウントのためアクセスできませんでした");
                                //既に削除されているユーザーの処理
                                request.getRequestDispatcher("/deleteduser.jsp").forward(request, response);
                            }
                        }
                }
            
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
