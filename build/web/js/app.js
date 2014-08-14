$(document).ready(function() {
    $(".autocomplete").autocomplete("AutoCompleteServlet");
    var counter = 0;
    $("#addButton").click(function () {   
        if(counter>10){
            alert("No more locations are allowed.");
            return false;
            }   
        var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + counter);
        newTextBoxDiv.after().html('<div class="form-group"><input type="text" name="inter' + 
        counter +'" id="inter' + counter + '" value="" placeholder="Intermediate Location" class="form-control autocomplete"></div>');
        
        newTextBoxDiv.appendTo("#TextBoxesGroup");
        $(".autocomplete").autocomplete("AutoCompleteServlet");
        counter++;
        });
        
     $("#delButton").click(function () {   
        if(counter==0){
            alert("Can not remove more locations.");
            return false;
            }   
        var Lbox = document.getElementById('TextBoxDiv'+(counter-1));
        Lbox.remove();
        counter--;
        });         
});

var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var maphome = new google.maps.LatLng(20.348485, 85.815724);
  var mapOptions = {
    zoom: 6,
    center: maphome
  }
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  directionsDisplay.setMap(map);
}

function calcRoute(list) {
  var start = list[0];
  var end = list[list.length-1];
  var waypts = [];
  for (var i = 1; i < (list.length-1); i++) {
      waypts.push({
          location:list[i],
          stopover:true});
    
  }

  var request = {
      origin: start,
      destination: end,
      waypoints: waypts,
      optimizeWaypoints: true,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
    }
  });
}

google.maps.event.addDomListener(window, 'load', initialize);