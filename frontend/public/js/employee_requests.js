function getRoute() {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/route",
    crossDomain: true,
    type: "GET",
    success: function(containerList) {
      return containerList;
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/empty/"+id,
    crossDomain: true,
    type: "POST",
    success: function(emptying) {
      return emptying;
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/report/"+id+"/fake",
    crossDomain: true,
    type: "POST",
    success: function(fake) {
      return fake;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom oznacavanja lazne prijave "+jqXHR);
    }
  });
}
