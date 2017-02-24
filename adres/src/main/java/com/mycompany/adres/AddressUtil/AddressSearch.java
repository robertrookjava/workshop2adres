/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adres.AddressUtil;
import java.math.BigDecimal;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author robertrook
 */
@Component
public class AddressSearch implements AddressSearchInterface {
    private String postcode;
    private String huisnummer;
    private String adres;
    
    @Override
    public void setPostcode (String postcode){
        this.postcode=postcode;
    }
    
    @Override
    public String getPostcode (){
        return this.postcode;
    }
    
    @Override
    public void setHuisnummer (String huisnummer){
        this.huisnummer= huisnummer;
    }
    
    @Override
    public String getHuisnummer(){
        return this.huisnummer;
    }
    
    @Override
    public void zoekAdres(){
        this.adres= geefAdres(this.postcode,this.huisnummer);
    }
    
    @Override
    public String getAdres(){
        return this.adres;
    }
    
    private String geefAdres (String postcode, String huisnummer){
        // hier de zoekfunctie echt implementeren
        String json = "";
        String url = maakURL (postcode, huisnummer);
        try {
            json = geefJSON (url);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return json;
        
        

    }
    
    private String geefJSON (String url) throws Exception {
        String json;
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

	//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

	int responseCode = con.getResponseCode();
	//System.out.println("\nSending 'GET' request to URL : " + url);
	//System.out.println("Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

		//print result
		//System.out.println(response.toString());
        json = response.toString();

        
        return json;
    }
            
    
    private String maakURL (String postcode, String huisnummer){
        String s = "http://rsas.rsvier.nl/uglyduck/postcodeData/?postcode=PPPP&huisnummer=HHHH&useKey=mt9c4wv7a13kydzq82be";
        String url = s.replaceFirst("PPPP", postcode);
        url = url.replaceFirst("HHHH", huisnummer);
        return url;

    }
    
    
    
    
   
}
