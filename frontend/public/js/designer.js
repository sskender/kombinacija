/*
Manipulating the look and feel of pages,
with respect to the user being logged in or not
*/

function handleDisplay() {
  var welcome = document.getElementById("welcome-msg");
  var login = document.getElementById("login-link");
  var logout = document.getElementById("logout");
  var regist = document.getElementById("register");
  var goAdmin = document.getElementById("go-admin");
  var goCitizen = document.getElementById("go-citizen");
  var goEmployee = document.getElementById("go-employee");



  var user = getLoggedInUser();
  if(user){
    welcome.innerHTML = "Dobrodošli, "+ user.name+"!";
    login.style.display = "none";
    regist.style.display = "none";
    logout.style.display = "block";
    goAdmin.style.display = "none";
    goEmployee.style.display = "none";
    goCitizen.style.display = "block";
    clearance(user.id, function(role){
      if(role=="admin"){
        goAdmin.style.display = "block";
      } else if(role=="employee"){
        goEmployee.style.display = "block";
      } else {
        return;
      }
    });
  } else {
    welcome.innerHTML = "Pomozite nam održati grad čistim";
    login.style.display = "block";
    regist.style.display = "block";
    logoutLink.style.display= "none";
    goAdmin.style.display = "none";
    goCitizen.style.display = "none";
    goEmployee.style.display = "none";
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

function rejectCitizen() {
  if(getLoggedInUser()){
    return;
  } else {
    window.location.href="index.html";
    return;
  }
}
