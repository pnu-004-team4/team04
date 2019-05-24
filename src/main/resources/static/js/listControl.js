// Sliders


//텍스트부터 서치 버튼 까지.
$(document).ready(function () { //화면에 html을 뿌려준 후에
    // 일어나는 일을 여기에 서술한다.
    //서밋 버튼을 누르면 일어나는 일.
    $("#sort-form").submit(function (event) {
        console.log("when push the button")
        //stop submit the form, we will post it manually.
        event.preventDefault(); //사이트 이동을 막아버렷.
        ajax_sort(); // 그 다음 이 메소드를 작동시킨다.
    });
});

//여기서부터 보면 되는건가

function ajax_sort(){
    var songname = {};
    songname["songname"] = $("#songname").val();

    var songname = $("#songname").val();

    var songname = {
        songname : $("#title-asc").val()
    };

    console.log("title-asc : " + songname);

    var date = "2019-05-07";

    //stringify: JSON 객체를 string으로 변환시켜주는 것.
    var jsonSongname = JSON.stringify(songname);
    console.log("JSONSONGNAME = " + jsonSongname);
    var jsonParseSongname = JSON.parse(jsonSongname);
    console.log("PARSENAME = " + jsonParseSongname);


    $.ajax({
        type: "POST", // http 요청 방식
        contentType: "application/json; charset=utf-8",
        url: "/player/searchmusic", // 요청할 서버
        data: JSON.stringify(songname), // 서버로 보낼 데이터
        //dataType: 'json',             // 서버에서 보내 줄 데이터 타입
        cache: false,
        timeout: 600000,
        success: [ function (data) {

            //이게 화면을 전환시켜주는 것 같은디...
            //<pre>: 입력한 그대로 보여주는 태그. 줄바꿈도 적용될 수 있다.
            var json = "<h4>Ajax Response</h4><pre> "
                + JSON.stringify(data, null, 4) + "</pre>";



            $('#feedback').html(json);  // json이라는 변수로 표기한다는 것 같다.

            console.log("SUCCESS : ", data);

        }],

        //에러 컨트롤러인 것 같고...
        error: [function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);

        }]
    });
}

function test_callAJAX(){
    document.getElementById('search-form').submit();
}
