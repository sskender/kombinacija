function getRoute(onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/route",
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      onsuccess(containers);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja rute "+jqXHR);
    }
  });
}

function emptyContainer(id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/empty/"+id,
    crossDomain: true,
    type: "POST",
    success: function(emptying) {
      alert("Kontejner ("+id+") uspjesno ispraznjen.");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom praznjenja kontejnera "+jqXHR);
    }
  });
}

function reportLegit(id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/report/"+id+"/legit",
    crossDomain: true,
    type: "POST",
    success: function(legit) {
      return legit;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom oznacavanja istinite prijave "+jqXHR);
    }
  });
}

function reportFake(id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/report/"+id+"/fake",
    crossDomain: true,
    type: "POST",
    success: function(fake) {
      alert("Obavijest o laznoj prijavi uspjesno poslana.");  
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom oznacavanja lazne prijave "+jqXHR);
    }
  });
}
