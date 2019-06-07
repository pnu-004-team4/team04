/*
*   $('#mycanvas').hide(); 숨길때
    $('#mycanvas').show(); 나타낼때.
    * 이렇게 쓰시면 될듯.
* */

var canvas = document.getElementById("loading_bar");
var context = canvas.getContext("2d");

//캔버스 크기 -> 배경에 대한 원의 비율이 결정.
canvas.width = 100;
canvas.height = 100;

//위치..
var x = canvas.width / 2;
var y = canvas.height / 2;

//stroke 반지름
var radius = 35;

// 색의 시작과 끝의 각도. 도는거
var startAngle = 0 * Math.PI;
var endAngle = 1.2 * Math.PI;
var counterClockwise = true;

//원 둘레의 반지름?
context.lineWidth = 4;
//몰라 이건.
context.strokeStyle = "white";

//시작 색깔인듯
var red = 255;
var blue = 0;
var green = 0;

// var redDone = false;
// var blueDone = false;
// var greenDone = false;

//빙빙 도는 메소드
var loop = function(){

    // 밝아지는 속도와 느려지는 속도
    startAngle = (startAngle + 0.025 * Math.PI)%(2*Math.PI);
    endAngle = (endAngle + 0.025 * Math.PI)%(2*Math.PI);

    if(red >= 255) {
        if(green > 0){
            green -= 5;
        }
        else{
            blue += 5;
        }
    }

    if(blue >= 255) {
        if(red > 0){
            red -= 5;
        }
        else{
            green += 5;
        }
    }
    if(green >= 255) {
        if(blue > 0){
            blue -= 5;
        }
        else{
            red += 5;
        }
    }

//stroke 투명도
    context.strokeStyle = "rgba("+red+","+green+","+blue+",1)";
    context.beginPath();
    context.arc(x, y, radius, 0 * Math.PI, 2 * Math.PI, counterClockwise);
    context.stroke();
    context.closePath();

    context.strokeStyle = "rgba(0,0,0,1)";
    context.beginPath();
    context.arc(x, y, radius, startAngle, endAngle, counterClockwise);
    context.stroke();
    context.closePath();


};

setInterval(loop, 10);
