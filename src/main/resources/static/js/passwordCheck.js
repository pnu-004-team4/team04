var strength=0;
const passwordLength = () => {
    let inputVal = document.querySelector("#password").value;
    const inputLen = document.querySelector("#inputLen");
    document.querySelector("#percent").style.color = 'transparent';

    if (inputVal.includes(" ")){
        inputLen.innerHTML  = "Whitespaces are not allowed";
        document.querySelector('.progress').style.display = 'none'
        return;
    }

    if (inputVal.length === 0) {
        inputLen.innerHTML = '';
        document.querySelector('.progress').style.display = "none";
        return;
    }

    if (inputVal.length < 6) {
        inputLen.innerHTML = "Minimum password length is 6";
        document.querySelector('.progress').style.display = 'none'
        return;
    }

    if (inputVal.length >= 6) {
        inputLen.innerHTML = "";
        document.querySelector('.progress').style.display = "inline-block";
    }

    if(inputVal.length > 12) {
        inputLen.innerHTML = "Maximum password length is 12";
        document.querySelector('.progress').style.display = "none"
        return;
    }

    const lower = /[a-z]/.test(inputVal);
    const upper = /[A-Z]/.test(inputVal);
    const digit = /[0-9]/.test(inputVal);
    const specialChar = /[$@#&!`~%^*()=+_-]/.test(inputVal);
    let passStrength = 0;
    if (lower){
        passStrength += 25;
        document.querySelector('#percent').innerHTML = passStrength;
    }
    if (upper) {
        passStrength += 25;
        document.querySelector('#percent').innerHTML = passStrength;
    }
    if (digit) {
        passStrength += 25;
        document.querySelector('#percent').innerHTML = passStrength;
    }
    if (specialChar) {
        passStrength += 25;
        document.querySelector('#percent').innerHTML = passStrength;
    }
    strength = passStrength;
    document.querySelector('#percent').innerHTML += "%";


    let percentage = document.querySelector("#percent").innerHTML;

    if (percentage === "25%"){
        document.querySelector('.progressBar').style.background = "red";
        document.querySelector('.progressBar').style.width = percentage;
    }
    if (percentage === "50%"){
        document.querySelector('.progressBar').style.background = "#FAD054";
        document.querySelector('.progressBar').style.width = percentage;
    }
    if (percentage === "75%"){
        document.querySelector('.progressBar').style.background = "blue";
        document.querySelector('.progressBar').style.width = percentage;
    }
    if (percentage === "100%"){
        document.querySelector('.progressBar').style.background = "green";
        document.querySelector('.progressBar').style.width = percentage;
    }

};
document.querySelector("#password").addEventListener("keyup", passwordLength);


function check() {
    if($('#password').val() != "" && strength < 75){
        alert("Password Security is too low.");
        $("#password").focus();
        return false;
    }else if(strength >= 75){
        if($("#password").val() != $("#cpassword").val()) {
            alert("Password does not match. Please check your password again");
            $("#cpassword").focus();
            return false;
        }
        return true;
    }
}
