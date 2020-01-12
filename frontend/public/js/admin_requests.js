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
      alert("Greška prilikom dohvacanja liste kontejnera "+jqXHR);
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
      alert("Greška prilikom dohvacanja kontejnera "+jqXHR);
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
      alert("Greška prilikom stvaranja kontejnera "+jqXHR);
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
      alert("Greška prilikom azuriranja kontejnera "+jqXHR);
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
      alert("Greška prilikom brisanja kontejnera "+jqXHR);
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
      alert("Greška prilikom dohvacanja liste radnika "+jqXHR);
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
      alert("Greška prilikom dohvacanja radnika "+jqXHR);
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
      alert("Greška prilikom stvaranja radnika "+jqXHR);
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
      return updated;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom azuriranja radnika "+jqXHR);
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
      alert("Greška prilikom brisanja radnika "+jqXHR);
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
      alert("Greška prilikom dohvacanja liste kvartova "+jqXHR);
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
      alert("Greška prilikom dohvacanja kontejnera iz kvarta "+jqXHR);
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
      alert("Greška prilikom dohvacanja radnika iz kvarta "+jqXHR);
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
      alert("Greška prilikom dohvacanja kvarta "+jqXHR);
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
      alert("Greška prilikom stvaranja komunalnog poduzeća "+jqXHR);
    }
  });
}

function putNeighborhood(hoodJSON, id){
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
    success: function(updated) {
      return updated;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom azuriranja kvarta "+jqXHR);
    }
  });
}

function deleteNeighborhood(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + user.bauth
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      alert("Susjedstvo "+id+" uspješno obrisano.");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja kvarta "+jqXHR);
    }
  });
}
