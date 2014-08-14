<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Map Project</title>
        <link rel="shortcut icon" href="img/Map.png">
        <link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
        <link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
        <script src="js/jquery-1.3.2.min.js"></script>
	<script src="js/jquery.autocomplete.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="js/app.js"></script>  
        <style>
            body {
    background-image: url('img/bg1.png');
}
        </style>
    </head>
    <body>
        <div class="container">
            <center><div class="headline"><img src="img/Map.png"></div><br/><h1>Map Project</h1></center>
            <input class="btn btn-success" type='button' value='Add More Locations' id='addButton'> <input class="btn btn-danger" type='button' value='Remove Location' id='delButton'><br/> <br/>
        <form method="POST" action="details.jsp">
        <input type="text" id="text1" name="start" placeholder="Source Location" class="form-control autocomplete"/><br/>
        <div id='TextBoxesGroup'></div>
        <input type="text" id="text1" name="end" placeholder="Destination Location" class="form-control autocomplete"/><br/>
        <center><input type="submit" value="Get Details" class="btn btn-info btn-hg"/></center>
        </form>
        <div>
    </body>
</html>