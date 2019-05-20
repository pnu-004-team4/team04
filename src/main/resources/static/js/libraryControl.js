/**
 * @description main에 해당하는 부분입니다. 이 부분에서 global한 작업이 정의됩니다.
 */
$(document).ready(function () {
  var librarySelectionList = $("#yourMusic").children();
  var libraryForm = $("#libraryForm");
  var selectedLibrary = $("#selectedLibrary");

  /**
   * @description callback 함수로 library의 list를 selection을 할 수 있습니다.
   */
  $.each(librarySelectionList, function (index, item) {
    $(item).click(function () {
      selectedLibrary.val(($(item).children("span")[0]).innerHTML);
      libraryForm.submit();
    }); // end of item click
  }); // end of for each
}); // end of main


