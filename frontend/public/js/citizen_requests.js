function ping(id, level, onsuccess){
  var user = getLoggedInUser();
  console.log("IN PING: USER: "+user);
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/ping/"+id+"/"+level,
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: "{}",
    success: onsuccess,
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom prijave kontejnera "+jqXHR);
    }
  });
}

function pingFull(id){
  return ping(id, "f", function() {
    alert("Kontejner "+id+" uspješno prijavljen kao pun.")
  });
}
function pingEmpty(id){
  return ping(id, "e", function() {
    alert("Kontejner "+id+" uspješno prijavljen kao prazan.")
  });
}
function pingUrgent(id){
  return ping(id, "u", function() {
    alert("Kontejner "+id+" uspješno prijavljen kao prepun.")
  });
}

function getFavorites(onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite/",
    crossDomain: true,
    type: "GET",
    success: function(favorites) {
      onsuccess(favorites);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste favorita "+jqXHR);
    }
  });
}

function postFavorite(id, onsuccess){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite/"+id,
    crossDomain: true,
    type: "POST",
    success: function(fav) {
      alert("Kontejner "+id+" uspješno dodan u favorite.");
      onsuccess(fav);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom stvaranja favorita "+jqXHR);
    }
  });
}

function deleteFavorite(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      alert("Kontejner "+id+" uspješno obrisan iz favorita.")
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja favorita "+jqXHR);
    }
  });
}
