<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import ="com.team04.musiccloud.utilities.sort.Music"
         import ="com.team04.musiccloud.utilities.sort.ListSort"
         import ="com.team04.musiccloud.utilities.Search.Search"
         import="java.util.ArrayList"
         language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%


    /*
     * search
     * @TODO null pointer exeption 처리
     * */
    Search search = new Search();
    String index = request.getParameter("index");

    ArrayList<Music> searchlist = search.msearch(index);


%>

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
    <form:form action="search" method="post">
        <div class="search">

            <input type="text" name = "index" placeholder="Search" />
        </div>
        <div class="Button">
            <button type="submit">Search</button>
        </div>
    </form:form>






</section>


<section class="content">

    <div class="content__middle">

        <div class="album__tracks">

            <div class="tracks">

                <div class="tracks__heading">

                    <div class="tracks__heading__number">#</div>

                    <div class="tracks__heading__title">Song</div>

                    <div class="tracks__heading__artist">ARTIST</div>

                    <div class="tracks__heading__length">


                    </div>


                </div>

                <%
                    for (int i = 0; i < searchlist.size(); i++) {
                %>
                <div class="track">

                    <div class="track__number"><%= i%></div>

                    <div class="track__play">

                        <i class="ion-ios-play"></i>
                    </div>
                    <div class="track__delete">

                        <i class="ion-android-delete"></i>
                    </div>

                    <%--title입니다.--%>
                    <div class="track__title"><%= searchlist.get(i).getTitle()%></div>

                    <%--artist--%>
                    <div class="track__artist"><%= searchlist.get(i).getArtist()%></div>

                </div>
                <%
                    }
                %>
            </div>

        </div>
    </div>

</section>


<%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>
<%--<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>--%>
<%--<script src='/js/nouislider.min.js'></script>--%>
<%--<script src="/js/myPlayer.js"></script>--%>

</body>
</html>
