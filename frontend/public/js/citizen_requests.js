function ping(id, level){
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
    success: function(ping) {
      return ping;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom prijave kontejnera "+jqXHR);
    }
  });
}

function pingFull(id){
  return ping(id, "f");
}
function pingEmpty(id){
  return ping(id, "e");
}
function pingUrgent(id){
  return ping(id, "u");
}

function getFavorites(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite",
    crossDomain: true,
    type: "GET",
    success: function(favorites) {
      return favorites;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste favorita "+jqXHR);
    }
  });
}

function postFavorite(id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/favorite/"+id,
    crossDomain: true,
    type: "POST",
    success: function(fav) {
      return fav;
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
      return deleted;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja favorita "+jqXHR);
    }
  });
}
