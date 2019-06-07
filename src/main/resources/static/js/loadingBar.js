/*
*   $('#mycanvas').hide(); 숨길때
    $('#mycanvas').show(); 나타낼때.
    * 이렇게 쓰시면 될듯.
* */

var canvas = document.getElementById('loading_bar');
var context = canvas.getContext('2d');

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
context.strokeStyle = 'white';

//시작 색깔인듯
var red = 255;
var blue = 0;
var green = 0;

var red_done = false;
var blue_done = false;
var green_done = false;

//test
// 색의 시작과 끝의 각도. 도는거
var e_startAngle = 0 * Math.PI;
var e_endAngle = 2 * Math.PI;


//빙빙 도는 메소드
var loop = function(){

    // 밝아지는 속도와 느려지는 속도
    startAngle = startAngle + 0.025 * Math.PI;
    endAngle = endAngle + 0.025 * Math.PI;

    if((red == 255) && (red_done == false)){
        blue += 5;
    }else if((blue == 255)  && (blue_done == false)){
        red -= 5;
    }else if((blue == 255) && (red == 0) && (green_done == false)){
        green += 5;
    }else if((green == 255) && (blue > 0)){
        blue -= 5;
    }else if((green == 255) && (blue == 0) && (red < 255)){
        red += 5;
    }else if(red == 255){
        green -= 5;
    }
    if(blue == 255){
        red_done = true;
    }
    if((blue == 255) && (red == 0)){
        blue_done = true;
    }
    if((blue == 255) && (green == 255)){
        green_done = true;
    }
    if((green_done == true) && (green == 0)){
        red_done = false;
        blue_done = false;
        green_done = false;
    }

//stroke 투명도
    context.strokeStyle = 'rgba('+red+','+green+','+blue+',1)';

    if(endAngle > (2*Math.PI)){
        endAngle = 0*Math.PI;
    }
    if(startAngle > (2*Math.PI)){
        startAngle = 0*Math.PI;
    }

    // context.fillStyle = 'rgba(24,24,24,0.05)';
    // context.fillRect(0, 0, 500, 300);
    context.beginPath();
    context.arc(x, y, radius, 0 * Math.PI, 2 * Math.PI, counterClockwise);
    context.stroke();
    context.closePath();

    context.strokeStyle = 'rgba(0,0,0,1)';
    context.beginPath();
    context.arc(x, y, radius, startAngle, endAngle, counterClockwise);
    context.stroke();
    context.closePath();


}

setInterval(loop, 10);
