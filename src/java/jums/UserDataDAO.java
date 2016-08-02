/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class UserDataDAO {
    
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    
        //ユーザーデータ登録時に用いるメソッド
        public void MakeAccount(UserDataDTO ud) throws SQLException{
            Connection con = null;
            PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMailaddress());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getTotal());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        
    /*
    *DBへのアクセスを通してログインを判断するメソッド。
    *ユーザー名とパスワードで検索処理を行う。
    *同時にユーザーID等の必要な情報を取り出し、セッションに入れて持ち回るための準備も担っている。
    */
    public ArrayList<UserDataDTO> forLogin(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from user_t where name = ? and password = ?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            ResultSet rs = st.executeQuery();
            UserDataDTO resultUd = new UserDataDTO();
            ArrayList<UserDataDTO> UserData = new ArrayList();
            while(rs.next()){
                
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setPassword(rs.getString(3));
                resultUd.setMailaddress(rs.getString(4));
                resultUd.setAddress(rs.getString(5));
                resultUd.setTotal(rs.getInt(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                resultUd.setDeleteFlg(rs.getInt(8));
                UserData.add(resultUd);
                
            }
            System.out.println("search completed");
            return UserData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
         
         
    //購入処理時の総金額の更新を行うメソッド
        public void updateTotal(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update user_t set total = total+? where userID=?");
            st.setInt(1, ud.getTotal());
            st.setInt(2, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
        
    //購入処理にて、購入した商品データの追加を行うメソッド
        public void BoughtProduct(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO buy_t(userID,itemcode,type,buyDate) VALUES(?,?,?,?)");
            st.setInt(1, ud.getUserID());
            st.setString(2, ud.getItemcode());
            st.setInt(3, ud.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /*
    *デリートフラグを起動するメソッド
    *ユーザーデータ削除処理は外部キー連結のために行うことができないので、
    *UPDETE処理で削除のフラグの数字を0から1にすることで対応する
    */
     public void userdataDelete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update user_t set deleteFlg = 1 where userID=?");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
     
     //ユーザーデータを更新するメソッド
        public void userUpdate(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update user_t set name=?,password=?,mail=?,address=?,newDate=? where userID=?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMailaddress());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        
    /*
    *商品購入履歴表示のためにuserIDに紐づけされた商品コードの取得を行うメソッド
    *UserIDでデータベースのbuy_tにアクセスし、商品コードの検索処理を行う。
    */
         public ArrayList<UserDataDTO> searchBoughtItemcode(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select itemCode from buy_t where userID = ?");
            st.setInt(1, ud.getUserID());
            ResultSet rs = st.executeQuery();
            
            //取り出したitemcodeをitemListに格納
            ArrayList<UserDataDTO> itemList = new ArrayList();
            while(rs.next()){
                UserDataDTO resultUd = new UserDataDTO();                
                resultUd.setItemcode(rs.getString(1));
                itemList.add(resultUd);
            }
            System.out.println("search completed");
            return itemList;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}
