// sort asc
function sortAsc(trackArray, target) {
  trackArray.sort(function (track1, track2) {
    var track1Lower = track1.querySelector(target).textContent.toLowerCase();
    var track2Lower = track2.querySelector(target).textContent.toLowerCase();
    return (track1Lower < track2Lower) ? (-1) : ((track1Lower > track2Lower) ? 1
        : 0);
  });
  return trackArray;
}

// sort desc
function sortDesc(trackArray, target) {
  trackArray.sort(function (track1, track2) {
    var track1Lower = track1.querySelector(target).textContent.toLowerCase();
    var track2Lower = track2.querySelector(target).textContent.toLowerCase();
    return (track1Lower < track2Lower) ? 1 : ((track1Lower > track2Lower) ? -1
        : 0);
  });
  return trackArray;
}

$(document).ready(function () {
  //검색 기능
  $("#search_key").keyup(function () {
    var key = $(this).val();  // 텍스트 필드에 입력된 값을 리턴.
    $(".track").hide();
    var temp = $(".track__title:contains('" + key + "')");
    var art = $(".track__artist:contains('" + key + "')");
    // console.log(temp);
    $(temp).parent().show();
    $(art).parent().show();
  });

  //overriding contains method
  jQuery.expr[":"].contains = function (a, i, m) {
    return jQuery(a).text().toUpperCase()
    .indexOf(m[3].toUpperCase()) >= 0;
  };

  //정렬 기능
  var tracks = document.querySelectorAll(".track"); // tracks nodelist
  var trackArray = Array.prototype.slice.call(tracks, 0); // node list to array
  var result;
  var sortMode = {"target": "", "isAsc": true};

  $("#trackNumSort").on("click", function () {
    updateSortMode("trackNum");
    updateList(".track__number");
  });

  $("#titleSort").on("click", function () {
    updateSortMode("title");
    updateList(".track__title");
  });

  $("#artistSort").on("click", function () {
    updateSortMode("artist");
    updateList(".track__artist");
  });

  $("#lengthSort").on("click", function () {
    updateSortMode("artist");
    updateList(".track__length");
  });

  // decided the sort mode according to current state
  function updateSortMode(target) {
    sortMode["isAsc"] = (sortMode["target"] === target) ? !sortMode["isAsc"]
        : true;
    sortMode["target"] = target;
  }

  // sort the list (both in the back and front)
  function updateList(target) {
    sortList(target);
    applyList();
  }

  // sorts the list (in the back)
  function sortList(target) {
    result = (sortMode["isAsc"]) ? sortAsc(trackArray, target) : sortDesc(
        trackArray, target);
  }

  // update the list in the front according to one in the back
  function applyList() {
    for (var i = 0; i < trackArray.length; i++) {
      $(".tracks").append(result[i]);
    }
  }
});
