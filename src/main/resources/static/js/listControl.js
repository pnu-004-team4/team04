
/**   sort는 javascript array sort method를 이용했고
 * 결과는 console.log로 확인할 수 있습니다.
 *    검색기능은 키보드를 누름과 동시에 DOM정보를 받아서 텍스트 필드에 있는
 * 값과 일치하는지를 확인한 후 일치하는 element(?)의 parent만 나타내는 것으로 구현했습니다.
 * */

function sortTitleASC(trackArray){
    trackArray.sort(function(a, b){
        return a.querySelector('.track__title').textContent.toLowerCase()
        < b.querySelector('.track__title').textContent.toLowerCase() ? -1
            : a.querySelector('.track__title').textContent.toLowerCase()
            > b.querySelector('.track__title').textContent.toLowerCase() ? 1 : 0;
    });
    return trackArray;
}

function sortTitleDSC(trackArray){
    trackArray.sort(function(a, b){
        return a.querySelector('.track__title').textContent.toLowerCase()
        < b.querySelector('.track__title').textContent.toLowerCase() ? 1
            : a.querySelector('.track__title').textContent.toLowerCase()
            > b.querySelector('.track__title').textContent.toLowerCase() ? -1 : 0;
    });
    return trackArray;
}

function sortArtistASC(trackArray){
    trackArray.sort(function(a, b){
        return a.querySelector('.track__artist').textContent.toLowerCase()
        < b.querySelector('.track__artist').textContent.toLowerCase() ? -1
            : a.querySelector('.track__artist').textContent.toLowerCase()
            > b.querySelector('.track__artist').textContent.toLowerCase() ? 1 : 0;
    });
    return trackArray;
}

function sortArtistDSC(trackArray){
    trackArray.sort(function(a, b){
        return a.querySelector('.track__artist').textContent.toLowerCase()
        < b.querySelector('.track__artist').textContent.toLowerCase() ? 1
            : a.querySelector('.track__artist').textContent.toLowerCase()
            > b.querySelector('.track__artist').textContent.toLowerCase() ? -1 : 0;
    });
    return trackArray;
}

$(document).ready(function () {
    //검색 기능
    $("#search_key").keyup(function(){
        var key = $(this).val();  // 텍스트 필드에 입력된 값을 리턴.
        $(".track").hide();
        var temp = $(".track__title:contains('" + key +"')");
        var art = $(".track__artist:contains('" + key +"')");
        // console.log(temp);
        $(temp).parent().show();
        $(art).parent().show();
    });

    //overriding contains method
    jQuery.expr[':'].contains = function(a, i, m) {
        return jQuery(a).text().toUpperCase()
            .indexOf(m[3].toUpperCase()) >= 0;
    };

    //정렬 기능
        var tracks = document.querySelectorAll('.track'); // tracks nodelist
        var trackArray = Array.prototype.slice.call(tracks, 0); // node list to array
        var result;


    $("#titleASC").on('click', function(){
        result = sortTitleASC(trackArray);
        console.log("==========sortTitleASC==========");
        for(var i =0; i < trackArray.length; i++){
            console.log(result[i].textContent);
        }
    });

    $("#titleDSC").on('click', function(){
        result = sortTitleDSC(trackArray);
        console.log("==========sortTitleDSC==========");
        for(var i =0; i < trackArray.length; i++){
            console.log(result[i].textContent);
        }
    });

    $("#artistASC").on('click', function(){
        result = sortArtistASC(trackArray);
        console.log("==========sortArtistASC==========");
        for(var i =0; i < trackArray.length; i++){
            console.log(result[i].textContent);
        }
    });

    $("#artistDSC").on('click', function(){
        console.log("==========sortArtistDSC==========");
        result = sortArtistDSC(trackArray);
        for(var i =0; i < trackArray.length; i++){
            console.log(result[i].textContent);
        }
    });

});
