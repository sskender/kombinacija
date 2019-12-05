// Authorization and authentication file

function doRegister() {
  var checked = document.getElementById("agree-term").checked;
  if(!checked){
    alert("Morate se složiti sa uvjetima korištenja kako biste se registrirali.");
    return;
  }
  if(document.getElementById("pass").value != document.getElementById("re_pass").value) {
    alert("Lozinke se ne podudaraju.");
    return;
  }

  var regDTO = {
    name: document.getElementById("name").value,
    lastName: document.getElementById("lastname").value,
    email: document.getElementById("email").value,
    pwd: document.getElementById("pass").value
  };

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
      window.location.replace("index.html");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom registracije - "+jqXHR);
      document.getElementById("pass").value = "";
      document.getElementById("re_pass").value = "";
    }
  });
}

function doLogin() {
  var email = document.getElementById("email").value;
  var pwd = document.getElementById("pass").value;

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
      window.location.replace("index.html");
    },
    error: function(jqXHR, textStatus, errorThrown){
      alert("Greška prilikom prijave - "+jqXHR);
      document.getElementById("pass").value = "";
    }
  });
}

function logMeOut() {
  window.localStorage.removeItem("basic-auth");
  window.localStorage.removeItem("user-name");
  window.localStorage.removeItem("user-id");
  window.localStorage.removeItem("user-email");
  window.location.replace("index.html");
}

function checkAuthentication() {
  if(window.localStorage.getItem("basic-auth")) {
    return true;
  } else {
    return false
  }
}

function getLoggedInUser() {
  var user = new Object();
  user.name = window.localStorage.getItem("user-name");
  user.email = window.localStorage.getItem("user-email");
  user.id = window.localStorage.getItem("user-id");
  user.bauth = window.localStorage.getItem("basic-auth");
  return user;
}
