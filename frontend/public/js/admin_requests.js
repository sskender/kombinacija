function getContainers(onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/container",
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      onsuccess(containers);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getOneContainer(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "GET",
    success: function(container) {
      onsuccess(container);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function postContainer(containerJSON){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/container",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(containerJSON),
    success: function(created) {
      alert("Kontejner uspješno stvoren, ID = "+created.id);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function putContainer(containerJSON, id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "PUT",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(containerJSON),
    success: function(updated) {
      alert("Kontej")
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function deleteContainer(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      alert("Kontejner "+id+" uspješno obrisan.")
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getEmployees(onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/employee",
    crossDomain: true,
    type: "GET",
    success: function(employees) {
      onsuccess(employees);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getOneEmployee(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/employee/"+id,
    crossDomain: true,
    type: "GET",
    success: function(employee) {
      onsuccess(employee);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function postEmployee(employeeJSON){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/employee",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(employeeJSON),
    success: function(created) {
      alert("Komunalni radnik uspješno stvoren, ID = "+created.id);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function putEmployee(employeeJSON, id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/employee/"+id,
    crossDomain: true,
    type: "PUT",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(employeeJSON),
    success: function(updated) {
      alert("Komunalni radnik uspješno premjesten.");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function deleteEmployee(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/employee/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      alert("Radnik "+id+" uspješno obrisan.")
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getNeighborhoods(onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood",
    crossDomain: true,
    type: "GET",
    success: function(hoods) {
      onsuccess(hoods);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getNeighborhoodContainers(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id+"/container",
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      onsuccess(containers);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getNeighborhoodEmployees(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id+"/employee",
    crossDomain: true,
    type: "GET",
    success: function(employees) {
      onsuccess(employees);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function getOneNeighborhood(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "GET",
    success: function(hood) {
      onsuccess(hood);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function postNeighborhood(hoodJSON){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(hoodJSON),
    success: function(created) {
      alert("Komunalno poduzeće "+created.name+" uspješno stvoreno.");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function putNeighborhood(hoodJSON, id, onsuccess){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "PUT",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(hoodJSON),
    success: onsuccess,
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}

function deleteNeighborhood(id, onsuccess) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "DELETE",
    success: onsuccess,
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA: "+jqXHR.responseJSON.message);
    }
  });
}
