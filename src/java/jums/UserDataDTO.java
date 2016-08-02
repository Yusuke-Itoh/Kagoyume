/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.sql.Timestamp;

/**
 * ユーザー情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * 
 * @author maimaimai
 */
public class UserDataDTO {
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
    
     public UserDataDTO(){
    this.name = "";
    this.password = "";
    this.mailaddress = "";
    this.address = "";
    this.total=0; //購入金額初期値
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
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMailaddress() {
        return mailaddress;
    }
    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }
    
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
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
    
}