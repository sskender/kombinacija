function getContainers() {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/container",
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

function getOneContainer(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "GET",
    success: function(container) {
      return container;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja kontejnera "+jqXHR);
    }
  });
}

function postContainer(containerJSON){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/container",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(containerJSON),
    success: function(created) {
      return created;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom stvaranja kontejnera "+jqXHR);
    }
  });
}

function putContainer(containerJSON, id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "PUT",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(containerJSON),
    success: function(updated) {
      return updated;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom azuriranja kontejnera "+jqXHR);
    }
  });
}

//Analogno za Employee i Neighborhood te sve njihove metode
