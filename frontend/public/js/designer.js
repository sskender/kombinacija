/*
Manipulating the look and feel of the main page,
with respect to the user being logged in or not
*/

function handleDisplay() {
  var welcomeElement = document.getElementById("welcome-msg");
  var loginLink = document.getElementById("login-link");
  var logoutLink = document.getElementById("logout");
  var regLink = document.getElementById("register");
  var defaultLoginDisplay = loginLink.display;
  var defaultBtnDisplay = logout.display;

  if(checkAuthentication()){
    welcomeElement.innerHTML = "Dobrodošli, "+ window.localStorage.getItem("user-name")+"!";
    loginLink.style.display = "none";
    regLink.style.display = "none";
    logoutLink.style.display = defaultBtnDisplay;
  } else {
    welcomeElement.innerHTML = "Pomozite nam održati grad čistim";
    loginLink.style.display = defaultLoginDisplay;
    regLink.style.display = defaultBtnDisplay;
    logoutLink.style.display= "none";
  }
}

function rejectLogin() {
  if(checkAuthentication()) {
    alert("Već ste prijavljeni u sustav kao " + window.localStorage.getItem("user-name"));
    window.location.replace("index.html");
  } else {
    return
  }
}

function rejectAdmin() {
  if(getLoggedInUser() && clearance(getLoggedInUser().id != "admin")){
    window.location.replace("index.html");
  } else {
    return;
  }
}
