/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adres;
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
import com.mycompany.adres.AddressUtil.AddressSearchInterface;
import java.util.Scanner;
import com.mycompany.adres.AddressUtil.AddressSearchInterface;


/**
 *
 * @author robertrook
 */
public class Application {
    
    @Autowired
    private AddressSearchInterface addressSearchInterface;
    
    public static void main(String[] args) {

        run();
       
    }
    
    public static void run(){
        
   
        String postcode;
        String huisnummer;
        String straat;
        String woonplaats;
        
        AnnotationConfigApplicationContext context = 
        new AnnotationConfigApplicationContext();
        
        context.register(Config.class);
        context.refresh();

        //MediaPlayer mediaplayer = context.getBean(MediaPlayer.class);
        //mediaplayer.play();
        
        AddressSearchInterface adressSearchInterface = context.getBean(AddressSearchInterface.class);
        
        
        
        
        Scanner input = new Scanner (System.in);
        System.out.print("Voer een postcode in ");
        postcode = input.nextLine();
        System.out.print("Voer een huisnummer in ");
        huisnummer = input.nextLine();
        
        adressSearchInterface.setPostcode(postcode);
        adressSearchInterface.setHuisnummer(huisnummer);
        adressSearchInterface.zoekAdres();
      
        straat = adressSearchInterface.getStraat();
        woonplaats = adressSearchInterface.getWoonplaats();
        
        
        if (adressSearchInterface.getFound()){
            System.out.println("Straat "+ straat);
            System.out.println("Woonplaats "+ woonplaats);   
        }
        else {
            System.out.println("Het adres is onbekend");
        }
        
        
        
        
        
        
    }
      
    
}
