// Sliders

var slider = document.getElementById('song-progress');

noUiSlider.create(slider, {
  start: [20],
  range: {
    'min': [0],
    'max': [100]
  }
});

var slider = document.getElementById('song-volume');

noUiSlider.create(slider, {
  start: [90],
  range: {
    'min': [0],
    'max': [100]
  }
});

function play() {
  var my_audio = document.getElementById('bgAudio');
  var music_totaltime = parseInt(my_audio.duration / 60) + ":" + parseInt(my_audio.duration % 60)
  document.getElementById("music_totaltime").innerHTML = music_totaltime;
  if (my_audio.paused == true) {
    my_audio.play();
    showPlayTime = setInterval(function () {
      var music_playtime = parseInt(my_audio.currentTime / 60) + ":" + parseInt(my_audio.currentTime % 60);
      document.getElementById("music_playtime").innerHTML = music_playtime;
    }, 500);
    $("#play").replaceWith('<a class="ion-ios-pause play" id = "pause" onclick="play()"></a>');
  } else {
    $("#pause").replaceWith('<a class="ion-ios-play play" id = "play" onclick="play()"></a>');
    my_audio.pause();
    clearInterval(showPlayTime);
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

  console.log(totalHeight);

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