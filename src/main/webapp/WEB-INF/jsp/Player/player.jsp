<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>pnu_004_team4 musiccloud</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900italic,900'
          rel='stylesheet' type='text/css'>


    <link rel='stylesheet prefetch'
          href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel='stylesheet prefetch'
          href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
    <link rel='stylesheet prefetch'
          href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
    <link rel='stylesheet prefetch' href='/css/nouislider.min.css'>

    <link rel="stylesheet" href="/css/myPlayer.css">
</head>

<body>

<%--Header Section--%>
<section class="header">
    <!--
    <div class="window__actions">
      <i class="ion-record red"></i>
      <i class="ion-record yellow"></i>
      <i class="ion-record green"></i>
    </div>
    -->


    <form:form action="search">
        <div class="search">
            <input type="text" id="search_key" placeholder="검색창"/>
        </div>
        <%--        <div class="Button">--%>
        <%--            <button type="submit"><i class="fa fa-search"></i></button>--%>
        <%--        </div>--%>
    </form:form>


    <div class="user">

        <div class="owner">

            <div class="user__info">
 
            <span class="user__info__name">
               <span class="first">Welcome!</span>
               <span class="last">${username}</span>
             </span>

            </div>

            <div class="user__actions">

                <div class="dropdown">
                    <button class="dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <i class="ion-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                        <li><a href="setting">Settings</a></li>
                        <li><a href="logout">Log Out</a></li>
                    </ul>
                </div>

            </div>
        </div>

    </div>

    <!--여긴어디-->
</section>

<%--Currenct Track & Control Section--%>
<section class="current-track">

    <div class="current-track__song">
        <a class="current-track__name">Some Type of Love</a>
        <a class="current-track__name">Charlie Puth</a>
    </div>

    <div class="current-track__actions">
        <a class="ion-ios-skipbackward"></a>
        <a class="ion-ios-play play" id="play" onclick="play()"></a>
        <a class="ion-ios-skipforward"></a>
    </div>

    <div class="current-track__progress">
        <div class="current-track__progress__start" id="music_playtime">START</div>
        <div class="current-track__progress__bar">
            <div id="song-progress"></div>
        </div>
        <div class="current-track__progress__finish" id="music_totaltime">END</div>
    </div>

    <div class="current-track__options">

        <a href="#" class="lyrics">Lyrics</a>
        <span class="controls">
            <a href="#" class="control"><i class="ion-shuffle"></i></a>
            <a href="#" class="control volume">
                <i class="ion-volume-high"></i>
                <div id="song-volume"></div>
            </a>
        </span>
    </div>
</section>

<%--Content Section--%>
<section class="content">

    <div class="content__left">

        <section class="navigation">
            <!-- Main -->
            <div class="navigation__list">
                <!-- Your Music -->
                <div class="navigation__list__header">

                    <div class="navigation__list__header"
                         role="button"
                         data-toggle="collapse"
                         href="#yourMusic"
                         aria-expanded="true"
                         aria-controls="yourMusic">
                        Your Music
                    </div>

                    <form:form action="/" id="libraryForm">
                        <div class="collapse in" id="yourMusic">

                            <!-- 만약 추가적인 라이브러리는 a tag를 추가하여 더 만들 수 있습니다. -->
                            <a href="#" class="navigation__list__item">
                                <i class="ion-headphone"></i>
                                <span>All Songs</span>
                            </a>

                            <a href="#" class="navigation__list__item">
                                <i class="ion-headphone"></i>
                                <span>Favorite Songs</span>
                            </a>

                            <!-- 아래 내용이 실제 POST의 parameter로 들어가는 부분입니다. -->
                            <input type="text" name="songs" value="" id="selectedLibrary" hidden>
                        </div>
                    </form:form>

                </div>

            </div>
        </section>


    </div>
    <div class="dropZone" id="dropZone">
        <div class="content__middle">
            <div class="album__tracks">
                <div class="tracks">
                    <div class="tracks__heading">

                        <div class="tracks__heading__number">#</div>
                        <div class="tracks__heading__title" id="titleSort">Song</div>
                        <div class="tracks__heading__artist" id="artistSort">ARTIST</div>
                        <div class="tracks__heading__length">
                            <i class="ion-ios-stopwatch-outline"></i>
                        </div>

                    </div>

                    <!-- DB에서 가져온 메타 값들을 넣어주는 부분입니다. -->
                    ${getLibrary}
                    <%--<div class="track">
                        <div class="track__number">1</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">7th Floor Tango</div>
                        <div class="track__artist">Silent Partner</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4154b138cd8314491cc10</div>
                    </div>
                    <div class="track">
                        <div class="track__number">2</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">Estampe Galactus# Barbare Epaulé Giraffe# Ennui</div>
                        <div class="track__artist">Monplaisir</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4159c138cd83ec8892211</div>
                    </div>
                    <div class="track" >
                        <div class="track__number">3</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">zero</div>
                        <div class="track__artist">null</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">나만, 봄</div>
                        <div class="track__artist">볼방탄 사춘기</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">그때가 좋았어</div>
                        <div class="track__artist">케이시(kassy)</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">옥탑방</div>
                        <div class="track__artist">엔플라잉</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">사계</div>
                        <div class="track__artist">방시혁</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">김윤수</div>
                        <div class="track__artist">박다온</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">welcome, 방탄</div>
                        <div class="track__artist">various artist</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">방탄조끼야</div>
                        <div class="track__artist">아저씨</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">작은 것들을 위한 시</div>
                        <div class="track__artist">방탄소년단</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">사랑에 연습이 있었다면</div>
                        <div class="track__artist">임재현</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">Ouverture</div>
                        <div class="track__artist">Evil needle</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>

                    <div class="track">
                        <div class="track__number">4</div>
                        <div class="track__play"><i class="ion-ios-play"></i></div>
                        <div class="track__delete"><i class="ion-android-delete"></i></div>
                        <div class="track__title">anywhere</div>
                        <div class="track__artist">Anderson.paak</div>
                        <div class="track__length">@TODO: TIME</div>
                        <div class="track__owner" hidden>admin@admin.com</div>
                        <div class="track__id" hidden>5ce4deb5a4975f3c5072fbda</div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
    </div>

</section>


<div type="hidden" id="userEmailDiv" value="${useremail}"></div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<script src='/js/nouislider.min.js'></script>
<script src="/js/libraryControl.js"></script>
<script src="/js/myPlayer.js"></script>
<script src="/js/listControl.js"></script>

${streaming}


</body>
</html>
