<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Bootstrap Admin App + jQuery">
    <meta name="keywords" content="app, responsive, jquery, bootstrap, dashboard, admin">
    <title>Listen Me</title>

    <!-- =============== VENDOR STYLES ===============-->
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <!-- FONT AWESOME-->
    <link rel="stylesheet" href="./resources/vendor/fontawesome/css/font-awesome.min.css">
    <!-- SIMPLE LINE ICONS-->
    <link rel="stylesheet" href="./resources/vendor/simple-line-icons/css/simple-line-icons.css">
    <!-- ANIMATE.CSS-->
    <link rel="stylesheet" href="./resources/vendor/animate.css/animate.min.css">
    <!-- WHIRL (spinners)-->
    <link rel="stylesheet" href="./resources/vendor/whirl/dist/whirl.css">
    <!-- =============== PAGE VENDOR STYLES ===============-->
    <!-- WEATHER ICONS-->
    <link rel="stylesheet" href="./resources/vendor/weather-icons/css/weather-icons.min.css">
    <!-- =============== BOOTSTRAP STYLES ===============-->
    <link rel="stylesheet" href="./resources/app/css/bootstrap.css" id="bscss">
    <!-- =============== APP STYLES ===============-->
    <link rel="stylesheet" href="./resources/app/css/app.css" id="maincss">
    <!-- DATATABLES-->
    <link rel="stylesheet" href="./resources/vendor/datatables-colvis/css/dataTables.colVis.css">
    <link rel="stylesheet" href="./resources/vendor/datatable-bootstrap/css/dataTables.bootstrap.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick-theme.css"/>
    <link rel="icon" href="./resources/logo-image/title-icon.ico" type="image/x-icon">

    <style>
        .content-wrapper > h3, /* .content-wrapper > .content-heading*/
        {
            margin-bottom: 4px;
            padding: 4px;
        }

        .wrapper > section {
            top: -20px;
        }

        .user-rating {
            direction: rtl;
            font-size: 20px;
            unicode-bidi: bidi-override;
            padding: 18px 10px;
            display: inline-block;
        }

        .user-rating input {
            opacity: 0;
            position: relative;
            left: -15px;
            z-index: 2;
            cursor: pointer;
        }

        .user-rating span.star:before {
            color: #777777;
            content: "ï€†";
            /*padding-right: 5px;*/
        }

        .user-rating span.star {
            display: inline-block;
            font-family: FontAwesome;
            font-style: normal;
            font-weight: normal;
            position: relative;
            z-index: 1;
        }

        .user-rating span {
            margin-left: -15px;
        }

        .user-rating span.star:before {
            color: #777777;
            content: "\f006";
            /*padding-right: 5px;*/
        }

        .user-rating input:hover + span.star:before, .user-rating input:hover + span.star  span.star:before, .user-rating input:checked + span.star:before, .user-rating input:checked + span.star  span.star:before {
            color: #ffd100;
            content: "\f005";
        }

        .selected-rating {
            color: #ffd100;
            font-weight: bold;
            font-size: 3em;
        }
        .genre-size:hover {
            -webkit-transform: scale(1.1);
            -moz-transform: scale(1.1);
            -ms-transform: scale(1.1);
            -o-transform: scale(1.1);
            /*transform: rotate scale(1.1);*/
            -webkit-transition: all 0.4s ease-in-out;
            -moz-transition: all 0.4s ease-in-out;
            -o-transition: all 0.4s ease-in-out;
            transition: all 0.4s ease-in-out;
        }

        button.viewmorebtn1:hover, button.viewmorebtn1:active {font-size: 120%;}
        button.viewmorebtn2:hover, button.viewmorebtn2:active {font-size: 120%;}

    </style>


</head>

<div class="wrapper">
    <jsp:include page="layout/header.jsp"/>
    <jsp:include page="layout/menu.jsp"/>

    <section style="margin-bottom: 10px !important;">
        <div class="content-wrapper">
            <div class="row">
                <div class="panel panel-default" style="margin-top: 20px">
                    <div class="panel-heading" style="background-color: #003153 !important; color: white !important;">
                        <b>Top Rated Movies</b>
                        <button class="btn btn-primary viewmorebtn1"  style="background-color: #003153;float: right;padding-bottom: 3px;padding-top: 3px;padding-left: 7px;padding-right: 6px;margin-top: -4px;">

                            <b> <a href="./topRatedMovies" style="color: whitesmoke">View More >>></a></b>

                        </button>

                    </div>
                    <div class="panel-body">
                        <div class="container">
                            <div class="row" id="topXRatedMovies">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section style="margin-bottom: 10px !important;">
        <div class="content-wrapper">
            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: #003153 !important; color: white !important;">
                        <b>Recommended Movies</b>
                    <button class="btn btn-primary viewmorebtn2"  style="background-color: #003153;float: right;padding-bottom: 3px;padding-top: 3px;padding-left: 7px;padding-right: 6px;margin-top: -4px;">

                        <b> <a href="./recommendedMovies" style="color: whitesmoke">View More >>></a></b>

                        </button>
                    </div>
                    <div class="panel-body" >
                        <div class="container">
                            <div class="row" id="recommendedMovies">
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="layout/footer.jsp"/>
</div>

<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                Loading. Please Wait...
                <div class="progress progress-striped active">
                    <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0"
                         aria-valuemax="100" style="width: 100%">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- MODERNIZR-->
<script src="./resources/vendor/modernizr/modernizr.js"></script>
<!-- JQUERY-->
<script src="./resources/vendor/jquery/dist/jquery.js"></script>
<!-- BOOTSTRAP-->
<script src="./resources/vendor/bootstrap/dist/js/bootstrap.js"></script>
<!-- STORAGE API-->
<script src="./resources/vendor/jQuery-Storage-API/jquery.storageapi.js"></script>
<!-- JQUERY EASING-->
<script src="./resources/vendor/jquery.easing/js/jquery.easing.js"></script>
<!-- ANIMO-->
<script src="./resources/vendor/animo.js/animo.js"></script>
<!-- SLIMSCROLL-->
<script src="./resources/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<!-- SCREENFULL-->
<script src="./resources/vendor/screenfull/dist/screenfull.js"></script>
<!-- LOCALIZE-->
<script src="./resources/vendor/jquery-localize-i18n/dist/jquery.localize.js"></script>
<!-- RTL demo-->
<script src="./resources/app/js/demo/demo-rtl.js"></script>
<!-- =============== PAGE VENDOR SCRIPTS ===============-->
<!-- SPARKLINE-->
<script src="./resources/app/vendor/sparklines/jquery.sparkline.min.js"></script>
<!-- FLOT CHART-->
<script src="./resources/vendor/Flot/jquery.flot.js"></script>
<script src="./resources/vendor/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
<script src="./resources/vendor/Flot/jquery.flot.resize.js"></script>
<script src="./resources/vendor/Flot/jquery.flot.pie.js"></script>
<script src="./resources/vendor/Flot/jquery.flot.time.js"></script>
<script src="./resources/vendor/Flot/jquery.flot.categories.js"></script>
<script src="./resources/vendor/flot-spline/js/jquery.flot.spline.min.js"></script>

<!-- CLASSY LOADER-->
<script src="./resources/vendor/jquery-classyloader/js/jquery.classyloader.min.js"></script>
<!-- MOMENT JS-->
<script src="./resources/vendor/moment/min/moment-with-locales.min.js"></script>
<!-- SKYCONS-->
<script src="./resources/vendor/skycons/skycons.js"></script>
<!-- DEMO-->
<script src="./resources/app/js/demo/demo-flot.js"></script>
<!-- =============== PAGE VENDOR SCRIPTS ===============-->
<script src="./resources/app/js/demo/demo-panels.js"></script>
<!-- =============== APP SCRIPTS ===============-->
<script src="./resources/app/js/app.js"></script>

<script src="resources/datatables/datatables/jquery.dataTables.js"></script>
<script src="resources/datatables/datatables/dataTables.colVis.min.js"></script>
<script src="resources/datatables/datatables/dataTables.tableTools.min.js"></script>
<script src="resources/datatables/datatables/dataTables.bootstrap.min.js"></script>
<script src="resources/datatable-responsive/datatable-responsive/datatables.responsive.min.js"></script>
<%--for glypicon--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--JS file for inserting user--%>
<script type="text/javascript" src="resources/slick/slick.js"></script>
<script src="/resources/app/js/test.js"></script>
<script src="/resources/app/js/home.js"></script>
</html>