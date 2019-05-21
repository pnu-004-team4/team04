/**
 * @description main에 해당하는 부분입니다. 이 부분에서 global한 작업이 정의됩니다.
 */
$(document).ready(function () {
  var librarySelectionList = $("#yourMusic").children();

  var libraryForm = $("#libraryForm");
  var selectedLibrary = $("#selectedLibrary");

  var trackList = $(".track");
  var firstTrack = trackList.first();

  var initialTrackName = firstTrack.find(".track__title")[0].innerText;
  var initialTrackArtist = firstTrack.find(".track__artist")[0].innerText;

  var currentTrackSong = $(".current-track__song").first();
  var currentTrackName = currentTrackSong.find(".current-track__name")[0];
  var currentTrackArtist = currentTrackSong.find(".current-track__name")[1];

  var MAX_TRACK_NAME_SIZE = 10;
  var MAX_TRACK_ARTIST_SIZE = 10;

  var audioTag = $("#bgAudio");

  currentTrackName.innerText = initialTrackName.slice(0, MAX_TRACK_NAME_SIZE);
  currentTrackArtist.innerText = initialTrackArtist.slice(0,
      MAX_TRACK_ARTIST_SIZE);

  /**
   * @description callback 함수로 library의 list를 selection을 할 수 있습니다.
   */
  $.each(librarySelectionList, function (index, item) {
    $(item).click(function () {
      selectedLibrary.val(($(item).children("span")[0]).innerHTML);
      libraryForm.submit();
    }); // end of item click
  }); // end of for each

  /**
   * @description track을 누르면 해당 track으로 갱신되도록 합니다.
   */
  trackList.each(function () {
    $(this).click(function () {
      var trackId = $(this).find(".track__id")[0].innerText;
      var trackName = $(this).find(".track__title")[0].innerText;
      var trackArtist = $(this).find(".track__artist")[0].innerText;

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
}); // end of main


