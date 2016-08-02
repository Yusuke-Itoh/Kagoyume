/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class UserData implements Serializable{
   ArrayList<ItemDataBeans> mycart = new ArrayList<ItemDataBeans>();
   //共通して用いる
    private int userID;//ユーザーID
    
    //user_tに用いる
    private String name;//ユーザー名
    private String password;//パスワード
    private String mailaddress;//メールアドレス
    private String address;//住所
    private Timestamp newDate;//更新日時
    private int total;//総購入金額
    private int deleteFlg;//削除フラグ
    
    //buy_tに用いる
    private int buyID;//購入ID
    private String itemcode;//商品コード
    private int type;//発送方法
    private Timestamp buyDate;//購入日時
   
   
 public UserData(){
    this.userID = 0;
    this.name = "";
    this.password = "";
    this.mailaddress = "";
    this.address = "";
    this.total = 0;
    this.deleteFlg = 0;
    this.buyID = 0;
    this.itemcode = "";
    this.type = 0;
 }

  //getter,setterの設定
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
   public String getName() {
        return name;
    }
   public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

   public String getPassword() {
        return password;
    }
   public void setPassword(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.password = "";
        }else{
            this.password = name;
        }
    }
   
   public String getMailaddress() {
        return mailaddress;
    }
   public void setMailaddress(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.mailaddress = "";
        }else{
            this.mailaddress = name;
        }
    }

   public String getAddress() {
        return address;
    }
   public void setAddress(String adress) {
        //空文字(未入力)の場合空文字をセット
        if(adress.trim().length()==0){
            this.address = "";
        }else{
            this.address = adress;
        }
    }
   
       public Timestamp getNewDate(){
        return newDate;
    }
    public void setNewDate(Timestamp newDate){
        this.newDate = newDate;
    }
    
    public int getTotal(){
        return total;
    }
    public void setTotal(int total){
        this.total = total;
    }
    
    public int getDeleteFlg(){
        return deleteFlg;
    }
    public void setDeleteFlg(int deleteFlg){
        this.deleteFlg = deleteFlg;
    }
    
    public int getBuyID(){
        return buyID;
    }
    public void setBuyID(int buyID){
        this.buyID = buyID;
    }
    
    public String getItemcode(){
        return itemcode;
    }
    public void setItemcode(String itemcode){
        this.itemcode = itemcode;
    }
    
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
    public Timestamp getBuyDate(){
        return buyDate;
    }
    public void setBuyDate(Timestamp buyDate){
        this.buyDate = buyDate;
    }

    //フォームに必要な情報が未記入かどうかをチェックするメソッド
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mailaddress.equals("")){
            chkList.add("mailaddress");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        return chkList;
    }


    
        //UDの中身をデータベースにアクセスするためにDTO型に変換するメソッド
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setUserID(this.userID);
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMailaddress(this.mailaddress);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
        udd.setDeleteFlg(this.deleteFlg);
        udd.setBuyID(this.buyID);
        udd.setItemcode(this.itemcode);
        udd.setType(this.type);
                
    }
    
    //データベースから取り出したDTO型のデータをUDで利用するために変換するメソッド
    public void DTO2UDMapping(UserDataDTO udd){
       this.setUserID(udd.getUserID());
       this.setName(udd.getName());
       this.setPassword(udd.getPassword());
       this.setMailaddress(udd.getMailaddress());
       this.setAddress(udd.getAddress());
       this.setTotal(udd.getTotal());
       this.setNewDate(udd.getNewDate());
       this.setDeleteFlg(udd.getDeleteFlg());
    }
   
}
