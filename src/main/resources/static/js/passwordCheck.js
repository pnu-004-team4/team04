var globalStrength = 0;
var globalPasswordModule = 0;
var globalPasswordLength = 0;

var MIN_PWD_LEN = 6;
var MAX_PWD_LEN = 12;

function checkTheEmpty(inputLen, passwordLength) {
  if (passwordLength !== 0) {
    inputLen.innerText = "Minimum password length is " + MIN_PWD_LEN;
  } else {
    inputLen.innerText = "";
  }
}

function checkTheLength(inputVal, inputLen) {
  if (inputVal.includes(" ")) {
    inputLen.innerText = "Whitespaces are not allowed";
    document.querySelector(".progress").style.display = "none";
    return false;
  }

  globalPasswordLength = inputVal.length;

  if (globalPasswordLength > MAX_PWD_LEN) {
    inputLen.innerText = "Maximum password length is " + MAX_PWD_LEN;
    document.querySelector(".progress").style.display = "none";
    return false;
  } else if (globalPasswordLength < MIN_PWD_LEN) {
    checkTheEmpty(inputLen, globalPasswordLength);
    document.querySelector(".progress").style.display = "none";
    return false;
  } else {
    inputLen.innerText = "";
    document.querySelector(".progress").style.display = "inline-block";
  }

  return true;
}

function checkPassStrengthStatus(state, passStrength) {
  if (state) {
    document.querySelector("#percent").innerText = String(passStrength);
    return 25;
  }
  return 0;
}

function getPassStrength(inputVal) {
  var lower = /[a-z]/.test(inputVal);
  var upper = /[A-Z]/.test(inputVal);
  var digit = /[0-9]/.test(inputVal);
  var specialChar = /[$@#&!`~%^*()=+_,.<>/?-]/.test(inputVal);
  var passStrength = 0;

  passStrength += checkPassStrengthStatus(lower, passStrength);
  passStrength += checkPassStrengthStatus(upper, passStrength);
  passStrength += checkPassStrengthStatus(digit, passStrength);
  passStrength += checkPassStrengthStatus(specialChar, passStrength);

  return passStrength;
}

function drawSecurityPercentage(basePercentage, percentage, color) {
  if (percentage === basePercentage) {
    document.querySelector(".progressBar").style.background = color;
    document.querySelector(".progressBar").style.width = percentage;
  }
}

function percentageCheck(percentage) {
  drawSecurityPercentage("25%", percentage, "red");
  drawSecurityPercentage("50%", percentage, "#FAD054");
  drawSecurityPercentage("75%", percentage, "blue");
  drawSecurityPercentage("100%", percentage, "green");
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
    document.querySelector("#percent").innerText += "%";

    var percentage = document.querySelector("#percent").innerText;
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
