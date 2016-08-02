/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class JumsHelper {
    
    
     //トップへのリンクを定数として設定
    private final String homeURL = "index.jsp";
    
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }
    
    //トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }

    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output += "パスワード";
                }
                if(val.equals("mailaddress")){
                    output += "メールアドレス";
                }
                if(val.equals("address")){
                    output += "住所";
                }
                
                output +="が未記入です。<br>";
            }
        return output;
    }
    
        public String exTypenum(int i){
        switch(i){
            case 1:
                return "郵送";
            case 2:
                return "コンビニ受け取り";
            case 3:
                return "その他";
        }
        return "";
    }
}
