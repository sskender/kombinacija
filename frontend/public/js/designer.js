/*
Manipulating the look and feel of pages,
with respect to the user being logged in or not
*/

function handleDisplay() {
  var welcomeElement = document.getElementById("welcome-msg");
  var loginLink = document.getElementById("login-link");
  var logoutLink = document.getElementById("logout");
  var regLink = document.getElementById("register");
  var defaultLoginDisplay = loginLink.display;
  var defaultBtnDisplay = logout.display;

  var user = getLoggedInUser();
  if(user){
    welcomeElement.innerHTML = "Dobrodošli, "+ user.name+"!";
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
  var user = getLoggedInUser();
  if(user) {
    alert("Već ste prijavljeni u sustav kao " + user.name + "("+user.email+")");
    window.location.href = "index.html";
  } else {
    return
  }
}

function rejectAdmin() {
  var user = getLoggedInUser();
  if(!user){
    window.location.href = "index.html";
    return;
  }
  clearance(user.id, function(role){
    if(role!="admin"){
      window.location.href = "index.html";
    }
  });
}
