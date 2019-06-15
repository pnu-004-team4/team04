var globalStrength = 0;
var globalPasswordModule = 0;
var globalPasswordLength = 0;

var MIN_PWD_LEN = 6;
var MAX_PWD_LEN = 12;

function checkTheLength(inputVal, inputLen) {
  if (inputVal.includes(" ")) {
    inputLen.innerHTML = "Whitespaces are not allowed";
    document.querySelector(".progress").style.display = "none";
    return false;
  }

  globalPasswordLength = inputVal.length;

  if (globalPasswordLength === 0) {
    inputLen.innerHTML = "";
    document.querySelector(".progress").style.display = "none";
    return false;
  }

  if (globalPasswordLength < MIN_PWD_LEN) {
    inputLen.innerHTML = "Minimum password length is " + MIN_PWD_LEN;
    document.querySelector(".progress").style.display = "none";
    return false;
  }

  if (globalPasswordLength >= MIN_PWD_LEN) {
    inputLen.innerHTML = "";
    document.querySelector(".progress").style.display = "inline-block";
  }

  if (globalPasswordLength > MAX_PWD_LEN) {
    inputLen.innerHTML = "Maximum password length is " + MAX_PWD_LEN;
    document.querySelector(".progress").style.display = "none";
    return false;
  }
  return true;
}

function getPassStrength(inputVal) {
  var lower = /[a-z]/.test(inputVal);
  var upper = /[A-Z]/.test(inputVal);
  var digit = /[0-9]/.test(inputVal);
  var specialChar = /[$@#&!`~%^*()=+_,.<>/?-]/.test(inputVal);
  var passStrength = 0;
  if (lower) {
    passStrength += 25;
    document.querySelector("#percent").innerHTML = String(passStrength);
  }
  if (upper) {
    passStrength += 25;
    document.querySelector("#percent").innerHTML = String(passStrength);
  }
  if (digit) {
    passStrength += 25;
    document.querySelector("#percent").innerHTML = String(passStrength);
  }
  if (specialChar) {
    passStrength += 25;
    document.querySelector("#percent").innerHTML = String(passStrength);
  }
  return passStrength;
}

function percentageCheck(percentage) {
  if (percentage === "25%") {
    document.querySelector(".progressBar").style.background = "red";
    document.querySelector(".progressBar").style.width = percentage;
  }
  if (percentage === "50%") {
    document.querySelector(".progressBar").style.background = "#FAD054";
    document.querySelector(".progressBar").style.width = percentage;
  }
  if (percentage === "75%") {
    document.querySelector(".progressBar").style.background = "blue";
    document.querySelector(".progressBar").style.width = percentage;
  }
  if (percentage === "100%") {
    document.querySelector(".progressBar").style.background = "green";
    document.querySelector(".progressBar").style.width = percentage;
  }
}

$(document).ready(function () {
  globalPasswordModule = function () {
    var inputVal = document.querySelector("#password").value;
    var inputLen = document.querySelector("#inputLen");
    document.querySelector("#percent").style.color = "transparent";

    if (!checkTheLength(inputVal, inputLen)) {
      return;
    }

    globalStrength = getPassStrength(inputVal);
    document.querySelector("#percent").innerHTML += "%";

    var percentage = document.querySelector("#percent").innerHTML;
    percentageCheck(percentage);
  };
  document.querySelector("#password").addEventListener("keyup",
      globalPasswordModule);
});

function check() {
  if (globalPasswordLength === 0) {
    alert("Empty NOT ALLOWED!!!");
    $("#password").focus();
    return false;
  }
  if (globalPasswordLength !== 0 && globalStrength < 75) {
    if (globalPasswordLength > MAX_PWD_LEN) {
      alert("Password is too long.");
    } else {
      alert("Password Security is too low.");
    }
    $("#password").focus();
    return false;
  }
  if ($("#password").val() !== $("#cpassword").val()) {
    alert(
        "Password does not match. Please check your password again");
    $("#cpassword").focus();
    return false;
  }
  return true;
}
