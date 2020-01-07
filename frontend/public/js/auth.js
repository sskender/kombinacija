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
  registerCitizen(regDTO);
}

function doLogin() {
  var email = document.getElementById("email").value;
  var pwd = document.getElementById("pass").value;
  testAuthorization(email, pwd);
}

function logMeOut() {
  window.localStorage.removeItem("basic-auth");
  window.localStorage.removeItem("user-name");
  window.localStorage.removeItem("user-id");
  window.localStorage.removeItem("user-email");
  window.location.href("index.html");
}

function getLoggedInUser() {
  if(window.localStorage.getItem("user-id")){
    var user = new Object();
    user.name = window.localStorage.getItem("user-name");
    user.email = window.localStorage.getItem("user-email");
    user.id = window.localStorage.getItem("user-id");
    user.bauth = window.localStorage.getItem("basic-auth");
    return user;
  } else {
    return null;
  }
}
