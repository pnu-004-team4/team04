var MAX_TRACK_NAME_SIZE = 30;
var MAX_TRACK_ARTIST_SIZE = 30;

/**
 * @description track을 누르면 해당 track으로 갱신되도록 합니다.
 */
function trackListClickerUpdate(){
  var audioTag = $("#bgAudio");
  var trackList = $(".track");

  trackList.each(function () {
    $(this).on("click",function(){
      var trackId = $(this).find(".track__id")[0].innerText;
      var trackName = $(this).find(".track__title")[0].innerText;
      var trackArtist = $(this).find(".track__artist")[0].innerText;

      var currentTrackSong = $(".current-track__song").first();
      var currentTrackName = currentTrackSong.find(".current-track__title")[0];
      var currentTrackArtist = currentTrackSong.find(
          ".current-track__author")[0];

      var musicPath = "download?id=" + trackId;

      currentTrackName.innerText = trackName.slice(0, MAX_TRACK_NAME_SIZE);
      currentTrackArtist.innerText = trackArtist.slice(0,
          MAX_TRACK_ARTIST_SIZE);
      $("#nowPlaying").attr("src", musicPath);
      audioTag[0].load();

      /*global play*/
      /*eslint no-undef: "error"*/
      play();
      });
    });

  var deleteDiv = $(".track__delete");
  deleteDiv.on("click",function(e){
    e.stopPropagation();
    e.preventDefault();
  });
}

/**
 * @description main에 해당하는 부분입니다. 이 부분에서 global한 작업이 정의됩니다.
 */
$(document).ready(function () {

  var librarySelectionList = $("#yourMusic").children();

  var libraryForm = $("#libraryForm");
  var selectedLibrary = $("#selectedLibrary");

  var trackList = $(".track");
  var firstTrack = trackList.first();

  var currentTrackSong = $(".current-track__song").first();
  var currentTrackName = currentTrackSong.find(".current-track__title")[0];
  var currentTrackArtist = currentTrackSong.find(".current-track__author")[0];

  var hasFirstTrackTitle = (firstTrack.find(".track__title")).firstChild;
  var hasFirstTrackArtist = firstTrack.find(".track__artist").firstChild;
  console.log(hasFirstTrackArtist+" "+hasFirstTrackTitle);

  if(hasFirstTrackTitle && hasFirstTrackArtist) {
    var initialTrackName = firstTrack.find(".track__title")[0].innerText;
    var initialTrackArtist = firstTrack.find(".track__artist")[0].innerText;

    currentTrackName.innerText = initialTrackName.slice(0, MAX_TRACK_NAME_SIZE);
    currentTrackArtist.innerText = initialTrackArtist.slice(0,
        MAX_TRACK_ARTIST_SIZE);
  }

  /**
   * @description callback 함수로 library의 list를 selection을 할 수 있습니다.
   */
  $.each(librarySelectionList, function (index, item) {
    $(item).click(function () {
      selectedLibrary.val(($(item).children("span")[0]).innerHTML);
      libraryForm.submit();
    }); // end of item click
  }); // end of for each

  trackListClickerUpdate();

}); // end of main


