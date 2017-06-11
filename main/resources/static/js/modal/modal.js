var loginModal = document.getElementById('loginModal');
var signUpModal = document.getElementById('signUpModal');

// Get the button that opens the modal
var login = document.querySelector(".login");
var signUp = document.querySelector(".sign-up");
// Get the <span> element that closes the modal
var loginClose = document.getElementsByClassName("close")[0];
var signUoClose = document.getElementsByClassName("close")[1];


// When the user clicks on the button, open the modal
login.onclick = function() {
  loginModal.style.display = "block";
}

signUp.onclick = function(){
  signUpModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
loginClose.onclick = function() {
  loginModal.style.display = "none";
}

signUoClose.onclick = function() {
  signUpModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == loginModal) {
      loginModal.style.display = "none";
  }

  if (event.target == signUpModal) {
      signUpModal.style.display = "none";
  }
}
