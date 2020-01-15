function ping(id, level, onsuccess){
  var user = getLoggedInUser();
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
      alert("GREŠKA "+jqXHR.responseJSON.message);
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
      alert("GREŠKA "+jqXHR.responseJSON.message);
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
      onsuccess();
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}

function deleteFavorite(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      alert("Kontejner "+id+" uspješno obrisan iz favorita.");
      onsuccess();
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}
