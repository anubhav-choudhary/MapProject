package edu.kiit.servlets;

import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anuj
 */
public class AutoCompleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+URLEncoder.encode(request.getParameter("q"), "UTF-8") +"&sensor=false&key=AIzaSyBlGk8bzlAX1Jw7vKdwb4uqDIiaPEHsNL0&components=country:in";
            listgen obj = new listgen();
            obj.dolistgen(new URL(url), out);
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}


class listgen  
{
  public void dolistgen( URL theURL , PrintWriter out)
  {

    HttpURLConnection con;
    int reCode;
    String reMessage;
    
    try
    {
      //get the HTTP response
      con = (HttpURLConnection)theURL.openConnection();
      reCode = con.getResponseCode();
      reMessage = con.getResponseMessage();
     // System.out.println("HTTP response and message: " + reCode  + " - " + reMessage );
      
      BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine,output="";
        while ((inputLine = in.readLine()) != null) 
            output=output+inputLine;
        in.close();
        
        //System.out.println(output);
        int offset=0,pos1,pos2;
        while(true)
        {
        offset = output.indexOf("\"description\"",offset+1);
        if(offset==-1) break;
        pos1 = output.indexOf("\"", offset+14);
        pos2 = output.indexOf("\"", pos1+1);
        String distanceblock = output.substring(pos1+1, pos2);
        out.println(distanceblock);
        
    }
    }
    catch( IOException e )
    {
      System.out.println( "GetResponse.GetResponse - error opening or reading URL: " + e );
    }
  }
 
}