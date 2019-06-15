// Sliders

var songProgressSlider = document.getElementById("song-progress");
var volumeSlider = document.getElementById("song-volume");

var isSongProgressSliderUsable = true;
var showPlayTime = null;

$(document).ready(function () {
  $("#loading_bar").hide();
  /*global noUiSlider*/
  /*eslint no-undef: "error"*/
  noUiSlider.create(songProgressSlider, {
    start: [0],
    connect: [false, true],
    range: {
      "min": [0],
      "max": [100]
    }
  });

  /*global noUiSlider*/
  /*eslint no-undef: "error"*/
  noUiSlider.create(volumeSlider, {
    start: 100,
    behaviour: "snap",
    connect: [false, true],
    range: {
      "min": 0,
      "max": 100
    }
  });

  songProgressSlider.noUiSlider.on("change", function (values, handle) {
    var myAudio = document.getElementById("bgAudio");
    var audioValue = values[handle];
    myAudio.currentTime = myAudio.duration * (audioValue / 100);
  });

  songProgressSlider.noUiSlider.on("start", function () {
    isSongProgressSliderUsable = false;
  });

  songProgressSlider.noUiSlider.on("end", function () {
    isSongProgressSliderUsable = true;
  });

  volumeSlider.noUiSlider.on("update", function (values, handle) {
    var myAudio = document.getElementById("bgAudio");
    var audioValue = values[parseInt(handle,10)];
    myAudio.volume = audioValue / 100;
  });
});

// Request Next, Prev
function getPlayingNode() {
  var myAudio = document.getElementById("nowPlaying");
  var currentAudioSrc = myAudio.getAttribute("src");

  var middle = currentAudioSrc.lastIndexOf("=");
  var dbID = currentAudioSrc.substring(middle + 1, currentAudioSrc.length);

  var children = $(".track").find(".track__id[value=" + dbID + "]")[0];

  if (typeof children !== "undefined" && typeof children.parentNode
      !== "undefined") {
    return children.parentNode;
  } else {
    throw "NoMatchingSong";
  }
}

function nextMusic() {
  var playingNode;

  try {
    playingNode = getPlayingNode();
  } catch (e) {
    console.log(e);
  }

  var nextSibling = playingNode.nextSibling;
  if (nextSibling !== null || typeof nextSibling !== "undefined") {
    nextSibling.click();
  } else {
    throw "There is No Next Song";
  }
}

function secondToText(duration) {
  if (isNaN(duration)) {
    return "Loading...";
  } else {
    var minuteText = parseInt(duration / 60, 10);
    var second = parseInt(duration % 60, 10);
    var secondText = (second >= 10) ? second : "0" + second;
    return minuteText + ":" + secondText;
  }
}

function playtimeUpdate(myAudio) {

  var musicProgressPercent;

  // Set Music Total Time
  // Set Music Play Time
  if (myAudio.paused === false) {
      showPlayTime = setInterval(function () {

      var musicTotalTime = secondToText(myAudio.duration);
      document.getElementById("music_playtime").innerText = secondToText(
          myAudio.currentTime);
      document.getElementById("music_totaltime").innerText = musicTotalTime;

      if (isSongProgressSliderUsable) {
        var progressPercent = myAudio.currentTime / myAudio.duration * 100;
        musicProgressPercent = songProgressSlider.noUiSlider.set(
            progressPercent);
      }

      if (myAudio.currentTime === myAudio.duration) {
        try {
          nextMusic();
        } catch (e) {
          songProgressSlider.noUiSlider.set(0);
          pause();
        }
      }
    }, 150);
  } else {
    clearInterval(showPlayTime);
  }
}

// Player Control
function play() {
  var myAudio = document.getElementById("bgAudio");
  $("#play").replaceWith(
      "<a class='ion-ios-pause play' id = 'pause' onclick='pause()'></a>");
  myAudio.play();
  playtimeUpdate(myAudio);
}

function pause() {
  var myAudio = document.getElementById("bgAudio");
  $("#pause").replaceWith(
      "<a class='ion-ios-play play' id = 'play' onclick='play()'></a>");
  myAudio.pause();
  playtimeUpdate(myAudio);
}






function prevMusic() {
  var playingNode;

  try {
    playingNode = getPlayingNode();
  } catch (e) {
    console.log(e);
  }

  var prevSibling = playingNode.previousSibling;
  if (prevSibling !== null || typeof prevSibling !== "undefined") {
    prevSibling.click();
  } else {
    console.log("There is No Prev Song");
  }
}

// Dropdown upload

$(document).ready(function () {
  fileDropDown();
});

function fileDropDown() {
  var dropZone = $("#dropZone");
  dropZone.on("dragenter", function (e) {
    e.stopPropagation();
    e.preventDefault();
    dropZone.css("background-color", "#282828");
  });
  dropZone.on("dragleave", function (e) {
    e.stopPropagation();
    e.preventDefault();
    dropZone.css("background-color", "#181818");
  });
  dropZone.on("dragover", function (e) {
    e.stopPropagation();
    e.preventDefault();
    dropZone.css("background-color", "#282828");
  });
  dropZone.on("drop", function (e) {
    console.log("drop event called");
    e.preventDefault();
    dropZone.css("background-color", "#181818");

    var files = e.originalEvent.dataTransfer.files;

    if (files != null) {
      if (files.length < 1) {
        alert("Cannot Upload Folder");
        return;
      }
      uploadFile(files)
    } else {
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
    var musicFileType = fileName.substring(musicMiddle + 1, fileName.length);
    var lowerCaseMusicFileType = musicFileType.toLowerCase();

    if (lowerCaseMusicFileType !== "mp3") {
      alert("Wrong File Format : " + lowerCaseMusicFileType);
    } else {
      var formData = new FormData();
      formData.append("file", files[parseInt(i, 10)]);
      $("#loading_bar").show();
      $.ajax({
        url: "/upload/" + userEmail,
        data: formData,
        type: "POST",
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        success: [function (result) {
          $("#dropZone").load(document.URL + " #dropZone", function () {
            $("#loading_bar").hide();
            /*global trackListClickerUpdate*/
            /*eslint no-undef: "error"*/
            trackListClickerUpdate();
          });
        }],
        error: [function (request, status, error) {
          alert("Error Code:" + request.status + "\n" + "Error Message:"
              + request.responseText + "\n" + "Error:" + error);
          $("#loading_bar").hide();
        }]
      });
    }
  }
}

// Deletion
function deleteMusic(dbId) {
  var isDelete = confirm("You really want to delete?");
  if(isDelete === true) {
    $.ajax({
      url: "/delete/" + dbId,
      type: "POST",
      enctype: "multipart/form-data",
      processData: false,
      contentType: false,
      cache: false,
      success: [function (result) {
        $("#dropZone").load(document.URL + " #dropZone", function () {
          alert(result);
          trackListClickerUpdate();
        });
      }]
    });
  }
}

// Tooltips
$(function () {
  $("[data-toggle='tooltip']").tooltip();
});

// Viewport Heights

$(window).on("resize load", function () {

  var totalHeight = $(window).height();

  var headerHeight = $(".header").outerHeight();
  var footerHeight = $(".current-track").outerHeight();
  var playlistHeight = $(".playlist").outerHeight();
  var nowPlaying = $(".playing").outerHeight();

  var navHeight = totalHeight - (headerHeight + footerHeight + playlistHeight
      + nowPlaying);
  var artistHeight = totalHeight - (headerHeight + footerHeight);

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