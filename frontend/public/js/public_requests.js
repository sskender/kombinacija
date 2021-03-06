function clearance(id, onsuccess){
  $.ajax({
    url: SERVER_URL + "/clearance?uid="+id,
    crossDomain: true,
    type: "GET",
    success: function(role) {
      onsuccess(role);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
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
      window.location.href = "index.html";
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
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
      window.location.href = "index.html";
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
      document.getElementById("pass").value = "";
    }
  });
}

function map(latitude, longitude, onsuccess) {
  $.ajax({
    url: SERVER_URL + "/map?lat=" + latitude + "&lon=" + longitude,
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      onsuccess(containers);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}

function mapHood(hoodId, onsuccess) {
  $.ajax({
    url: SERVER_URL + "/map/"+hoodId,
    crossDomain: true,
    type: "GET",
    success: function(containers) {
      onsuccess(containers);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}

function getHoods(onsuccess) {
  $.ajax({
    url: SERVER_URL + "/hoods",
    crossDomain: true,
    type: "GET",
    success: function(hoods) {
      onsuccess(hoods);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}

function getHistory(contId, onsuccess){
  $.ajax({
    url: SERVER_URL + "/history/container/"+contId,
    crossDomain: true,
    type: "GET",
    success: function(events){
      onsuccess(events);
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("GREŠKA "+jqXHR.responseJSON.message);
    }
  });
}
