/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author maimaimai
 */
public class AccessYahooAPI {
    
    public String getResult(String urlString){
    String result = "";
    try{
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String tmp = "";
        while ((tmp = in.readLine()) != null) {
        result += tmp;
        }
    in.close();
    con.disconnect();
        }catch(Exception e){
    e.printStackTrace();
    }
    return result;
}
    public JsonNode getJsonNode(String jsonString){
       JsonNode head = null;
       try{
       JsonFactory jfactory = new JsonFactory();
       JsonParser parser = jfactory.createParser(jsonString);
       ObjectMapper mapper = new ObjectMapper();
       head = mapper.readTree(parser);
       }catch(Exception e){
       e.printStackTrace();
    }
      return head;
    }
    
    
    
}
