function clearance(id){
  $.ajax({
    url: SERVER_URL + "/clearance?uid="+id,
    crossDomain: true,
    type: "GET",
    success: function(role) {
      return role;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja autoriteta "+jqXHR);
    }
  });
}

function map(latitude, longitude) {
  $.ajax({
    url: SERVER_URL + "/map?lat=" + latitude + "&lon=" + longitude,
    crossDomain: true,
    type: "GET",
    success: function(containerList) {
      return containerList;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste kontejnera "+jqXHR);
    }
  });
}

function mapHood(hoodId) {
  $.ajax({
    url: SERVER_URL + "/map/"+hoodId,
    crossDomain: true,
    type: "GET",
    success: function(containerList) {
      return containerList;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste kontejnera"+jqXHR);
    }
  });
}
