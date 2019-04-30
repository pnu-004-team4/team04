<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>pnu_004_team4 musiccloud</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900italic,900' rel='stylesheet' type='text/css'>


    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
    <link rel='stylesheet prefetch' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
    <link rel='stylesheet prefetch' href='/css/nouislider.min.css'>

    <link rel="stylesheet" href="/css/myPlayer.css">
</head>

<body>

<section class="header">

    <!--
    <div class="window__actions">
      <i class="ion-record red"></i>
      <i class="ion-record yellow"></i>
      <i class="ion-record green"></i>
    </div>
    -->


    <div class="search">

        <input type="text" placeholder="" />

    </div>

    <div class="Button">
        <button type="submit">Search</button>
    </div>


    <div class="user">

        <div class="owner">


            <div class="user__info">


            <span class="user__info__name">

        <span class="first">Adam</span>

        <span class="last">Lowenthal</span>

      </span>

            </div>

            <div class="user__actions">

                <div class="dropdown">
                    <button class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <i class="ion-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">

                        <li><a href="setting">Settings</a></li>
                        <li><a href="#">Log Out</a></li>
                    </ul>
                </div>

            </div>
        </div>

    </div>
</section>



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


      <a href="#" class="control">
        <i class="ion-shuffle"></i>
      </a>


      <a href="#" class="control volume">

        <i class="ion-volume-high"></i>

        <div id="song-volume"></div>

      </a>

    </span>

    </div>
</section>

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

                    <div class="collapse in" id="yourMusic">

                        <a href="#" class="navigation__list__item">
                            <i class="ion-headphone"></i>
                            <span>Songs</span>
                        </a>

                        <a href="#" class="navigation__list__item">
                            <i class="ion-ios-musical-notes"></i>
                            <span>Albums</span>
                        </a>

                        <a href="#" class="navigation__list__item">
                            <i class="ion-person"></i>
                            <span>Artists</span>
                        </a>

                        <a href="#" class="navigation__list__item">
                            <i class="ion-document"></i>
                            <span>Local Files</span>
                        </a>

                    </div>

                </div>
                <!-- / -->

                <!-- Playlists -->
                <div class="navigation__list">

                    <div class="navigation__list__header"
                         role="button"
                         data-toggle="collapse"
                         href="#playlists"
                         aria-expanded="true"
                         aria-controls="playlists">
                        Playlists
                    </div>

                    <div class="collapse in" id="playlists">


                        <a href="#" class="navigation__list__item">
                            <i class="ion-ios-musical-notes"></i>
                            <span>Pop Classics</span>
                        </a>

                        <a href="#" class="navigation__list__item">
                            <i class="ion-ios-musical-notes"></i>
                            <span>Love Songs</span>
                        </a>


                        <a href="#" class="navigation__list__item">
                            <i class="ion-ios-musical-notes"></i>
                            <span>Sleep</span>
                        </a>

                    </div>

                </div>
                <!-- / -->
            </div>
        </section>

        <section class="playlist">

            <a href="#">

                <i class="ion-ios-plus-outline"></i>

                New Playlist

            </a>

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

                    </div>


                </div>

                <div class="track">

                    <div class="track__number">1</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                    <div class="track__title">Intro</div>

                    <div class="track__artist">OGiJun</div>

                    <div class="track__length">1:11</div>

                </div>

                <div class="track">

                    <div class="track__number">2</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                    <div class="track__title">Random</div>


                    <div class="track__artist">GwangSoo</div>

                    <div class="track__length">3:00</div>


                </div>

                <div class="track">

                    <div class="track__number">3</div>


                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                    <div class="track__title featured">

                        <span class="title">Me, Myself & I</span>
                        <span class="feature">feat. Bebe Rexha</span>

                    </div>


                    <div class="track__artist">YongJun</div>

                    <div class="track__length">4:11</div>


                </div>

                <div class="track">

                    <div class="track__number">4</div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>

                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>
                    <div class="track__title featured">

                        <span class="title">One Of Them</span>
                        <span class="feature">feat. Big Sean</span>

                    </div>


                    <div class="track__artist">OnSub</div>


                    <div class="track__length">3:20</div>



                </div>


            </div>

        </div>
    </div>

</section>


<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<script src='/js/nouislider.min.js'></script>
<script src="/js/myPlayer.js"></script>

${streamingTest}


</body>
</html>
