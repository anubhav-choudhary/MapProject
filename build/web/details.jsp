<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.kiit.servlets.distance_calculator"%>
<%@page import="edu.kiit.servlets.Placedetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
String setDistanceFormat(long dist)
{
    String res= "";
    if(dist>1000)
    {
        float dis=dist/1000;
        res=String.valueOf(dis)+" KM";
    }
    else
    {
        res=String.valueOf(dist) + " M";;
    }
    return res;
}

String setTimeFormat(long dura)
{
 if(dura>=3600)
 {
     long hr = dura/3600;
     long min = (dura-hr*3600)/60;
     long sec = (int)(dura-hr*3600)%60;
     return String.valueOf(min) +" Mins "+String.valueOf(sec)+ " Sec";
 }
 else if(dura>=60)
 {
     long min = dura/60;
     long sec = (int)dura%60;
     return String.valueOf(min) +" Mins "+String.valueOf(sec)+ " Sec";
 }
 else
 {
     return String.valueOf(dura)+ " Sec";
 }
}
%>


<%
try {
                String start,end;
                start=request.getParameter("start");
                end=request.getParameter("end");
                ArrayList intermediate = new ArrayList();
                int i=0;
                while(!(request.getParameter("inter"+i)==null))
                {
                    intermediate.add(request.getParameter("inter"+i));
                    i++;
                }

                distance_calculator obj = new distance_calculator();
                Placedetails pls = new Placedetails(start);
                obj.setLat1(pls.getLat());
                obj.setLng1(pls.getLongi());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <title>Map Project</title>
        <link rel="shortcut icon" href="img/Map.png">
        <link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
        <link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
        <script src="js/jquery-1.3.2.min.js"></script>
	<script src="js/jquery.autocomplete.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
        <script src="js/app.js"></script> 
        <style>
            body {
    background-image: url('img/bg1.png');
}
        </style>
    </head>
    <body onLoad='calcRoute([<%
            out.print("\""+start+"\" , ");
            Iterator list = intermediate.iterator();
            while(list.hasNext())
            {
                String interm = (String)list.next();
                out.print("\""+interm+"\" , ");
            }
            out.print("\""+end+"\"");
%>]);'>
        <div class="container">
        <center><div class="headline"><img src="img/Map.png"></div><br/><h1>Map Project</h1></center>
        <div class="container">
        <div class="pull-left"><input class="btn btn-success" type='button' value='Toggle Map' id="togglemap" onClick='$("#map-canvas").toggle();'></div>
        <div class="pull-right"><a href="index.jsp"><button class="btn btn-info">Back</button></a></div>
        </div><br/>
        <div class="container">
            <div id="map-canvas" style="width:100%;border:2px solid black; height: 300px"></div>
        </div>
        <br />
        <div class="container">
        <center>
        <table class="table table-hover">
        <tr><tr><th>Place</th><th>Latitude</th><th>Longitude</th><th>Distance</th><th>Time</th></tr></tr>
<%
                out.println("<tr><td>"+pls.getLocation()+"</td><td>"+pls.getLat()+"</td><td>"+pls.getLongi()+"</td><td>0</td><td>0</td></tr>");
                
                if(i>0)
                {
                 pls.setLocation((String)intermediate.get(0));
                 obj.setLat2(pls.getLat());
                 obj.setLng2(pls.getLongi());
                 obj.cal_details();
                 String dist=setDistanceFormat(obj.getDistance());
                out.println("<tr><td>"+pls.getLocation()+"</td><td>"+pls.getLat()+"</td><td>"+pls.getLongi()+"</td><td>"+dist+"</td><td>"+setTimeFormat(obj.getTime())+"</td></tr>");
                }
                
                for(int j=1;j<i;j++)
                {
                pls.setLocation((String)intermediate.get(j));
                obj.add_place(pls.getLat(),pls.getLongi());  
                String dist=setDistanceFormat(obj.getDistance());
                out.println("<tr><td>"+pls.getLocation()+"</td><td>"+pls.getLat()+"</td><td>"+pls.getLongi()+"</td><td>"+dist+"</td><td>"+setTimeFormat(obj.getTime())+"</td></tr>");
                }
                pls.setLocation(end);
                if(i>0)
                {
                obj.add_place(pls.getLat(),pls.getLongi());
                }
                else
                {
                    obj.setLat2(pls.getLat());
                    obj.setLng2(pls.getLongi());
                    obj.cal_details();
                }
                String dist=setDistanceFormat(obj.getDistance());
                out.println("<tr><td>"+pls.getLocation()+"</td><td>"+pls.getLat()+"</td><td>"+pls.getLongi()+"</td><td>"+dist+"</td><td>"+setTimeFormat(obj.getTime())+"</td></tr></table>");
                
        } finally {            
            out.close();
        }
%>
        </table>
        </center>
        </div>
        </div>
    </body>
</html>