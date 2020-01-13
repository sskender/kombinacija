function handleDisplay() {
  var welcomeElement = document.getElementById("welcome-msg");
  var loginLink = document.getElementById("login-link");
  var logoutLink = document.getElementById("logout");
  var regLink = document.getElementById("register");

  var user = getLoggedInUser();
  if(user){
    welcomeElement.innerHTML = "Dobrodošli, "+ user.name+"!";
    loginLink.style.display = "none";
    regLink.style.display = "none";
    logoutLink.style.display = "block";
  } else {
    welcomeElement.innerHTML = "Pomozite nam održati grad čistim";
    loginLink.style.display = "block";
    regLink.style.display = "block";
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

function rejectCitizen() {
  if(getLoggedInUser()){
    return;
  } else {
    window.location.href="index.html";
    return;
  }
}

function rejectEmployee() {
  var user = getLoggedInUser();
  if(!user){
    window.location.href = "index.html";
    return;
  }
  clearance(user.id, function(role){
    if(role!="employee"){
      window.location.href = "index.html";
    }
  });
}
