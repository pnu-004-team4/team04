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

    <form:form action="search" method="get">
        <div class="search">
            <input type="text" name = "index" placeholder="Search" />

        </div>
    </form:form>

    <div class="Button">
        <button type="submit">
            <i class="fa fa-search"></i>
        </button>
    </div>

    <div class="user">

    <form:form action="search">
        <div class="search">
            <input type="text" name="index" placeholder="Search"/>
        </div>
        <div class="Button">
            <button type="submit"><i class="fa fa-search"></i></button>
        </div>
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
</section>

<%--Currenct Track & Control Section--%>
<section class="current-track">

    <div class="current-track__song">
        <a class="current-track__name">Some Type of Love</a>
<<<<<<< HEAD

        <a class="current-track__artist">Charlie Puth</a>

=======
        <a class="current-track__name">Charlie Puth</a>
>>>>>>> 07057ed916e39b1fd2073bae10bc82c56e4f7016
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
                                <span>Some Songs</span>
                            </a>

                            <!-- 아래 내용이 실제 POST의 parameter로 들어가는 부분입니다. -->
                            <input type="text" name="songs" value="" id="selectedLibrary" hidden>
                        </div>
                    </form:form>

                </div>

            </div>
        </section>


    </div>
    <div class="content__middle">
        <div class="album__tracks">
            <div class="tracks">
                <div class="tracks__heading">

                    <div class="tracks__heading__number">#</div>
                    <div class="tracks__heading__title">Song</div>
                    <div class="tracks__heading__artist">ARTIST</div>
                    <div class="tracks__heading__length">
                        <i class="ion-ios-stopwatch-outline"></i>
<<<<<<< HEAD

                    </div>


                </div>

                <div class="track">

                    <div class="track__number">1</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>

                    <div class="track__title">Intro</div>

                    <div class="track__artist">OGiJun</div>

                    <div class="track__length">1:11</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <div class="track">

                    <div class="track__number">2</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">Random</div>


                    <div class="track__artist">GwangSoo</div>

                    <div class="track__length">3:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <div class="track">

                    <div class="track__number">3</div>


                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>

                    <div class="track__title featured">

                        <span class="title">Me, Myself & I</span>
                        <span class="feature">feat. Bebe Rexha</span>

                    </div>


                    <div class="track__artist">YongJun</div>

                    <div class="track__length">4:11</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <div class="track">

                    <div class="track__number">4</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title featured">

                        <span class="title">One Of Them</span>
                        <span class="feature">feat. Big Sean</span>

                    </div>


                    <div class="track__artist">OnSub</div>

                    <div class="track__length">3:20</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <div class="track">

                    <div class="track__number">5</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">Rom</div>


                    <div class="track__artist">GgSoo</div>

                    <div class="track__length">3:50</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <div class="track">

                    <div class="track__number">6</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">dom</div>


                    <div class="track__artist">ngSoo</div>

                    <div class="track__length">6:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">7</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">m</div>


                    <div class="track__artist">oo</div>

                    <div class="track__length">7:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">8</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">asdom</div>


                    <div class="track__artist">광수</div>

                    <div class="track__length">8:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">9</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">123m</div>


                    <div class="track__artist">123Soo</div>

                    <div class="track__length">9:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">10</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">uium</div>


                    <div class="track__artist">휘성</div>

                    <div class="track__length">10:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">11</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">ooom</div>


                    <div class="track__artist">&&gSoo</div>

                    <div class="track__length">11:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">12</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">12andom</div>


                    <div class="track__artist">거미ngSoo</div>

                    <div class="track__length">12:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">13</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">89*dom</div>


                    <div class="track__artist">)(*&*Soo</div>

                    <div class="track__length">13:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">14</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">ㅜㅡㅜㅡㅜㅡㅜdom</div>


                    <div class="track__artist">ㅗㅓㅗ</div>

                    <div class="track__length">14:00</div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
=======
>>>>>>> 07057ed916e39b1fd2073bae10bc82c56e4f7016
                    </div>

                </div>

<<<<<<< HEAD

                    <div class="track__title">아쉬워</div>


                    <div class="track__artist">Go</div>

                    <div class="track__length">15:00</div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                </div>
                <div class="track">

                    <div class="track__number">16</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">Rando874m</div>


                    <div class="track__artist">Gwa765ngS234oo</div>

                    <div class="track__length">16:00</div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>
                <div class="track">

                    <div class="track__number">17</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>


                    <div class="track__title">jklkjandom</div>


                    <div class="track__artist">ioipoiangSoo</div>

                    <div class="track__length">17:00</div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                </div>

                <!-- DB에서 가져온 메타 값들을 넣어주는 부분입니다. -->
                ${getLibrary}

            </div>

        </div>
    </div>

</section>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<script src='/js/nouislider.min.js'></script>
<script src="/js/myPlayer.js"></script>
<script src="/js/libraryControl.js"></script>

${streamingTest}

<footer class="footer">...</footer>
</body>
</html>
