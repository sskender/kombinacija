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

function deleteContainer(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/container/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      return deleted;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja kontejnera "+jqXHR);
    }
  });
}

function getEmployees() {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/employee",
    crossDomain: true,
    type: "GET",
    success: function(employees) {
      return employees;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste radnika "+jqXHR);
    }
  });
}

function getOneEmployee(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/employee/"+id,
    crossDomain: true,
    type: "GET",
    success: function(employee) {
      return employee;
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/employee",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(employeeJSON),
    success: function(created) {
      return created;
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/employee/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      return deleted;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja radnika "+jqXHR);
    }
  });
}

function getNeighborhoods() {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood",
    crossDomain: true,
    type: "GET",
    success: function(hoods) {
      return hoods;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja liste kvartova "+jqXHR);
    }
  });
}

function getNeighborhoodContainers(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood/"+id+"/container",
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      return containers;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja kontejnera iz kvarta "+jqXHR);
    }
  });
}

function getNeighborhoodEmployees(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood/"+id+"/employee",
    crossDomain: true,
    type: "GET",
    success: function(employees) {
      return employees;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom dohvacanja radnika iz kvarta "+jqXHR);
    }
  });
}

function getOneNeighborhood(id) {
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "GET",
    success: function(hood) {
      return hood;
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(hoodJSON),
    success: function(created) {
      return created;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom stvaranja kvarta "+jqXHR);
    }
  });
}

function putNeighborhood(hoodJSON, id){
  var user = getLoggedInUser();
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
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
      "Authorization": "Basic " + btoa(user.email + ":" + user.pwd)
    },
    url: SERVER_URL + "/neighborhood/"+id,
    crossDomain: true,
    type: "DELETE",
    success: function(deleted) {
      return deleted;
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom brisanja kvarta "+jqXHR);
    }
  });
}
