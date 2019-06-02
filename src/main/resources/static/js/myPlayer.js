// Sliders

var songProgressSlider = document.getElementById('song-progress');
var volumeSlider = document.getElementById('song-volume');

var isSongProgressSliderUsable = true;

$(document).ready(function(){
  noUiSlider.create(songProgressSlider, {
    start: [0],
    connect : [false,true],
    range: {
      'min': [0],
      'max': [100]
    }
  });

  noUiSlider.create(volumeSlider, {
    start: 100,
    behaviour:'snap',
    connect : [false,true],
    range: {
      'min': 0,
      'max': 100
    }
  });

  songProgressSlider.noUiSlider.on('change',function(values,handle){
    var my_audio = document.getElementById('bgAudio');
    my_audio.currentTime = my_audio.duration * (values[handle]/100);
  });

  songProgressSlider.noUiSlider.on('start',function(){
    isSongProgressSliderUsable = false;
  })

  songProgressSlider.noUiSlider.on('end',function(){
    isSongProgressSliderUsable = true;
  });

  volumeSlider.noUiSlider.on('update',function(values,handle){
    var my_audio = document.getElementById('bgAudio');
    my_audio.volume = values[handle]/100;
  });
});

// Player Control
function play() {
  var my_audio = document.getElementById('bgAudio');
  if(my_audio.paused == true){
    togglePlayPauseButton(my_audio);
    my_audio.play();
    playtimeUpdate(my_audio);
  }
}

function pause() {
  var my_audio = document.getElementById('bgAudio');
  if(my_audio.paused == false){
    togglePlayPauseButton(my_audio);
    my_audio.pause();
    playtimeUpdate(my_audio);
  }
}

function togglePlayPauseButton(my_audio){
  // Changing attributes of button
  if(my_audio.paused == true){
    // Change 'pause button' to 'play button'
    $("#play").replaceWith('<a class="ion-ios-pause play" id = "pause" onclick="pause()"></a>');
  } else{
    // Change 'play button' to 'pause button'
    $("#pause").replaceWith('<a class="ion-ios-play play" id = "play" onclick="play()"></a>');
  }
}

function secondToText(duration){
  if(isNaN(duration)){
    return "Loading...";
  }
  else{
    var minuteText = parseInt(duration / 60);
    var second = parseInt(duration % 60);
    var secondText = (second>=10)? second : "0"+second;
    var music_playtime = minuteText + ":" + secondText;

    return music_playtime;
  }
}

function playtimeUpdate(my_audio){

  var music_progress_percent;

  // Set Music Total Time
  // Set Music Play Time
  if (my_audio.paused == false) {
    showPlayTime = setInterval(function () {

      var musicTotalTime = secondToText(my_audio.duration);
      var musicPlayTime = secondToText(my_audio.currentTime);

      document.getElementById("music_playtime").innerHTML = musicPlayTime;
      document.getElementById("music_totaltime").innerHTML = musicTotalTime;

      if(isSongProgressSliderUsable){
        progress_percent = my_audio.currentTime / my_audio.duration * 100;
        music_progress_percent = songProgressSlider.noUiSlider.set(progress_percent);
      }
    }, 150);

  } else {
    clearInterval(showPlayTime);
  }
}

// Deletion

$(document).ready(function(){
    $(".track__delete").on("click",function(e){
      e.preventDefault();
      e.stopPropagation();
    });
});

function deleteMusic(dbId){
  console.log("delete Music called");

  $.ajax({
    url: "/delete/" + dbId,
    type: 'POST',
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: [function (result) {
      $('#dropZone').load(document.URL +  ' #dropZone', function(){
        alert(result);
        trackListClickerUpdate();
      });
    }]
  });

}

// Dropdown upload

$(document).ready(function (){
  fileDropDown();
});

function fileDropDown(){
  var dropZone = $("#dropZone");
  dropZone.on('dragenter',function(e){
    e.stopPropagation();
    e.preventDefault();
    dropZone.css('background-color','#282828');
  });
  dropZone.on('dragleave',function(e){
    e.stopPropagation();
    e.preventDefault();
    dropZone.css('background-color','#181818');
  });
  dropZone.on('dragover',function(e){
    e.stopPropagation();
    e.preventDefault();
    dropZone.css('background-color','#282828');
  });
  dropZone.on('drop',function(e){
    console.log("drop event called");
    e.preventDefault();
    dropZone.css('background-color','#181818');

    var files = e.originalEvent.dataTransfer.files;

    if(files != null){
      if(files.length < 1){
        alert("Cannot Upload Folder");
        return;
      }
      uploadFile(files)
    }else{
      alert("ERROR");
    }
  });
}

function uploadFile(files) {

  var userEmail = document.getElementById("userEmailDiv").getAttribute("value");

  for (var i = 0; i < files.length; i++) {

    // Type Checking part, @TODO - Extract Methods
    var fileName = files[i].name;
    var musicMiddle = fileName.lastIndexOf(".");
    var musicFileType = fileName.substring(musicMiddle+1,fileName.length);
    var lowerCaseMusicFileType = musicFileType.toLowerCase();

    if(lowerCaseMusicFileType != "mp3"){
      alert("Wrong File Format : " + lowerCaseMusicFileType);
    }
    else{
      var formData = new FormData();
      formData.append('file', files[i]);

      $.ajax({
        url: "/upload/" + userEmail,
        data: formData,
        type: 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: [function (result) {
          $('#dropZone').load(document.URL +  ' #dropZone', function(){
            alert(result);
            /*global trackListClickerUpdate*/
            /*eslint no-undef: "error"*/
            trackListClickerUpdate();
          });
        }]
      });
    }
  }
}

// Tooltips

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

// Viewport Heights

$(window).on("resize load", function () {

  var totalHeight = $(window).height();

  var headerHeight = $('.header').outerHeight();
  var footerHeight = $('.current-track').outerHeight();
  var playlistHeight = $('.playlist').outerHeight();
  var nowPlaying = $('.playing').outerHeight();

  var navHeight = totalHeight - (headerHeight + footerHeight + playlistHeight + nowPlaying);
  var artistHeight = totalHeight - (headerHeight + footerHeight);

  // console.log(totalHeight);

  $(".navigation").css("height", navHeight);
  $(".artist").css("height", artistHeight);
  $(".social").css("height", artistHeight);

});


// Collapse Toggles

$(".navigation__list__header").on("click", function () {

  $(this).toggleClass("active");

});

// Media Queries

$(window).on("resize load", function () {
  if ($(window).width() <= 768) {

    $(".collapse").removeClass("in");
    $(".navigation").css("height", "auto");
    $(".artist").css("height", "auto");

  }
});

$(window).on("resize load", function () {
  if ($(window).width() > 768) {

    $(".collapse").addClass("in");

  }
});