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

function registerCitizen(regDTO){
  $.ajax({
    url: SERVER_URL + "/register",
    crossDomain: true,
    type: "POST",
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    data: JSON.stringify(regDTO),
    success: function(userJSON) {
      window.localStorage.setItem('basic-auth', btoa(regDTO.email+":"+regDTO.pwd));
      window.localStorage.setItem('user-name', userJSON.name);
      window.localStorage.setItem('user-id', userJSON.id);
      window.localStorage.setItem('user-email', userJSON.email);
      window.location.href("index.html");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom registracije - "+jqXHR);
      document.getElementById("pass").value = "";
      document.getElementById("re_pass").value = "";
    }
  });
}

function testAuthorization(email, pwd){
  $.ajax({
    headers: {
      "Authorization": "Basic " + btoa(email + ":" + pwd)
    },
    url: SERVER_URL + "/auth",
    crossDomain: true,
    type: "GET",
    success: function(userJSON) {
      window.localStorage.setItem('basic-auth', btoa(email+":"+pwd));
      window.localStorage.setItem('user-name', userJSON.name);
      window.localStorage.setItem('user-id', userJSON.id);
      window.localStorage.setItem('user-email', userJSON.email);
      window.location.href("index.html");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom prijave - "+jqXHR);
      document.getElementById("pass").value = "";
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
