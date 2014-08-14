
package edu.kiit.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Placedetails {
    private String location;
    private String lat;
    private String longi;
    private String ref;

    public Placedetails(String location) {
        this.location = location;
        fetch_details();
    }
    
    private void fetch_details()
    {
        try{
        String url1 ="https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+URLEncoder.encode(location, "UTF-8") +"&sensor=false&key=AIzaSyBlGk8bzlAX1Jw7vKdwb4uqDIiaPEHsNL0";
        URL theURL = new URL(url1);
      HttpURLConnection con;
      con = (HttpURLConnection)theURL.openConnection();
      
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine,output="";
        while ((inputLine = in.readLine()) != null) 
            output=output+inputLine;
        in.close();
        
        //System.out.println(output);
        int offset = output.lastIndexOf("reference");
        int pos1 = output.indexOf(":", offset);
        int pos2 = output.indexOf("\"", pos1+4);
        ref = output.substring(pos1+3, pos2);
        
        String url2="https://maps.googleapis.com/maps/api/place/details/json?reference="+URLEncoder.encode(ref, "UTF-8") +"&key=AIzaSyBlGk8bzlAX1Jw7vKdwb4uqDIiaPEHsNL0&sensor=false";
        URL theURL2 = new URL(url2);
        con=(HttpURLConnection)theURL2.openConnection();
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        output="";
        while ((inputLine = in.readLine()) != null) 
            output=output+inputLine;
        in.close();
        
        offset = output.lastIndexOf("\"lat\"");
        pos1 = output.indexOf(":", offset);
        pos2 = output.indexOf(",", pos1+4);
        lat = output.substring(pos1+2, pos2);
        
        offset = output.lastIndexOf("\"lng\"");
        pos1 = output.indexOf(":", offset);
        pos2 = output.indexOf(" ", pos1+4);
        longi = output.substring(pos1+2, pos2);
        
        
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

    public String getRef() {
        return ref;
    }

    
    
    public String getLat() {
        return lat;
    }

    public String getLocation() {
        return location;
    }

    public String getLongi() {
        return longi;
    }

    public void setLocation(String location) {
        this.location = location;
        fetch_details();
    }    
}
