/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */

//商品関連のJavaBeans
public class ItemDataBeans implements Serializable{
    private String img;//画像
    private String name;//商品の名前
    private int price;//値段
    private String itemcode;//商品コード
    private String descript;//商品説明
    private double rate;//レビューの点数
    
public ItemDataBeans(){
    img  = "";
    name = "";
    price = 0;
    itemcode = "";
    descript = "";
    rate = 0;    
}

public String getImg(){
    return img;
}

public void setImg(String img){
    this.img = img;    
}

public String getName(){
    return name;    
}

public void setName(String name){
    this.name = name;
}

public int getPrice(){
    return price;
}

public void setPrice(String price){
    this.price = Integer.parseInt(price);
}

public String getItemcode(){
    return itemcode;
}

public void setItemcode(String itemcode){
    this.itemcode= itemcode;
}

public String getDescript(){
    return descript;
}

public void setDescript(String descript){
    this.descript= descript;
}

public double getRate(){
    return rate;
}

public void setRate(String rate){
    this.rate = Double.parseDouble(rate);
}

 public void DTO2IDBMapping(UserDataDTO udd){
       this.setName(udd.getName());

    }





}

