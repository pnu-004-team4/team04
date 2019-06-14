var sortMode = {"target": "", "isAsc": true};

function getTrackByStatus(track, target) {
  var processedTrack = null;
  if (target.search("number") === -1) {
    processedTrack = track.querySelector(target).textContent.toLowerCase();
  } else {
    processedTrack = parseInt(track.querySelector(target).textContent.toLowerCase());
  }
  return processedTrack;
}

// sort asc
function sortAsc(trackArray, target) {
  trackArray.sort(function (track1, track2) {
    var result = 0;

    var track1Lower = getTrackByStatus(track1, target);
    var track2Lower = getTrackByStatus(track2, target);

    if (track1Lower < track2Lower) {
      result = -1;
    } else if (track1Lower > track2Lower) {
      result = 1;
    }
    return result;
  });

  return trackArray;
}

// sort desc
function sortDesc(trackArray, target) {
  trackArray.sort(function (track1, track2) {
    var result = 0;

    var track1Lower = getTrackByStatus(track1, target);
    var track2Lower = getTrackByStatus(track2, target);

    if (track1Lower < track2Lower) {
      result = 1;
    } else if (track1Lower > track2Lower) {
      result = -1;
    }
    return result;
  });

  return trackArray;
}

// update the list in the front according to one in the back
function applyList(trackArray) {
  var length = trackArray.length;
  for (var i = 0; i < length; i++) {
    $(".tracks").append(trackArray[i]);
  }
}

// sorts the list (in the back)
function sortList(trackArray, target) {
  var sortedArray;

  if (sortMode["isAsc"]) {
    sortedArray = sortAsc(trackArray, target);
  } else {
    sortedArray = sortDesc(trackArray, target);
  }
  return sortedArray;
}

// decided the sort mode according to current state
function updateSortMode(target) {
  if (sortMode["target"] === target) {
    sortMode["isAsc"] = !sortMode["isAsc"];
  } else {
    sortMode["isAsc"] = true;
  }

  sortMode["target"] = target;
}

function sortTarget(target) {
  var tracks = document.querySelectorAll(".track");
  var trackArray = Array.prototype.slice.call(tracks, 0);

  updateSortMode(target);
  trackArray = sortList(trackArray, target);
  applyList(trackArray);
}



$(document).ready(function () {
  //검색 기능
  $("#search_key").keyup(function () {
    var key = $(this).val();  // 텍스트 필드에 입력된 값을 리턴.
    $(".track").hide();
    var temp = $(".track__title:contains('" + key + "')");
    var art = $(".track__artist:contains('" + key + "')");
    $(temp).parent().show();
    $(art).parent().show();
  });

  //overriding contains method
  jQuery.expr[":"].contains = function (a, i, m) {
    return jQuery(a).text().toUpperCase()
    .indexOf(m[3].toUpperCase()) >= 0;
  };
});
