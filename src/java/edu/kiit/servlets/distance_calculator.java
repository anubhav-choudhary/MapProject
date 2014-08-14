package edu.kiit.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class distance_calculator {
    
    private String lat1;
    private String lng1;
    private String lat2;
    private String lng2;
    long distance;
    long time;

    public distance_calculator() {
    }

    
    
    public long getDistance() {
        return distance;
    }

    public long getTime() {
        return time;
    }

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLng1() {
        return lng1;
    }

    public void setLng1(String lng1) {
        this.lng1 = lng1;
    }

    public String getLng2() {
        return lng2;
    }

    public void setLng2(String lng2) {
        this.lng2 = lng2;
    }

    public distance_calculator(String lat1, String lng1, String lat2, String lng2) {
        this.lat1 = lat1;
        this.lng1 = lng1;
        this.lat2 = lat2;
        this.lng2 = lng2;
        this.distance=0;
        this.time=0;
        cal_details();
    }


    
   public void cal_details()
   {
       try{
   String url1 ="http://maps.googleapis.com/maps/api/distancematrix/json?origins="+lat1+","+lng1+"&destinations="+lat2+","+lng2+"&sensor=false";
    
        URL theURL = new URL(url1);
      HttpURLConnection con;
      con = (HttpURLConnection)theURL.openConnection();
      
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine,output="";
        while ((inputLine = in.readLine()) != null) 
            output=output+inputLine;
        in.close();  
        
        int offset = output.lastIndexOf("distance");
        int pos1 = output.indexOf("value", offset);
        int pos2 = output.indexOf("}", offset);
        distance += Integer.parseInt(output.substring(pos1+8, pos2).trim());
        
        
        
        offset = output.lastIndexOf("duration");
        pos1 = output.indexOf("value", offset);
        pos2 = output.indexOf("}", offset);
        time += Integer.parseInt(output.substring(pos1+8, pos2).trim());
       }
       catch(UnsupportedEncodingException e)
        {
        ;
        }
        catch(MalformedURLException e)
        {
            ;
        }
        catch(IOException e)
        {
            ;
        }
        
   }
   
   public void add_place(String lat, String lng)
   {
       lat1=lat2;
       lng1=lng2;
       lat2=lat;
       lng2=lng;
       cal_details();
   }
   
}
