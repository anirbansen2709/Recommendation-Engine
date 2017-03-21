<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 07-Mar-17
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick-theme.css"/>
</head>


<%@ include file="./uiFrame.jsp" %>

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

    .user-rating input:hover + span.star:before, .user-rating input:hover + span.star ~ span.star:before, .user-rating input:checked + span.star:before, .user-rating input:checked + span.star ~ span.star:before {
        color: #ffd100;
        content: "\f005";
    }

    .selected-rating {
        color: #ffd100;
        font-weight: bold;
        font-size: 3em;
    }
</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<body>
<section style="margin-bottom: 10px !important;">

    <div class="content-wrapper">
        <%--<h3>Device management--%>
        <%--<small>Add new SNMP devices</small>--%>
        <%--</h3>--%>
        <!--Top Rated Songs -->
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color: #003153 !important; color: white !important;"><b>Top Rated Songs</b></div>
                <div class="panel-body" style="height: 180px; background-color: #007BA7">
                    <div class="container">
                        <div class="row" id="topXRatedSongs">
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%--<div class="row">--%>
            <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">Top Rated Songs</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--<div class="container">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-md-3 col-sm-3"--%>
                                     <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                    <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                        <%--padding-left: -1px; margin-left: -30px">--%>
                                        <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                             <%--width="304" height="236" style="max-width: 115%">--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-6 col-sm-6">--%>
                                        <%--smit--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="col-md-3 col-sm-3"--%>
                                     <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                    <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                       <%--padding-left: -1px; margin-left: -30px">--%>
                                        <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                             <%--width="304" height="236" style="max-width: 115%">--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-6 col-sm-6">--%>
                                        <%--smit--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="col-md-3 col-sm-3"--%>
                                     <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                    <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                    <%--padding-left: -1px; margin-left: -30px">--%>
                                        <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                             <%--width="304" height="236" style="max-width: 115%">--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-6 col-sm-6">--%>
                                        <%--smit--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="col-md-3 col-sm-3"--%>
                                     <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                    <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                    <%--padding-left: -1px; margin-left: -30px">--%>
                                        <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive"--%>
                                             <%--alt="" width="304" height="236" style="max-width: 115%">--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-6 col-sm-6">--%>
                                        <%--smit--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="panel panel-default">--%>
                <%--<div class="panel-heading">Top Rated Songs</div>--%>
                <%--<div class="panel-body">--%>
                    <%--<div class="container">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-3 col-sm-3"--%>
                                 <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                    <%--padding-left: -1px; margin-left: -30px">--%>
                                    <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                         <%--width="304" height="236" style="max-width: 115%">--%>
                                <%--</div>--%>
                                <%--<div class="col-md-6 col-sm-6">--%>
                                    <%--smit--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="col-md-3 col-sm-3"--%>
                                 <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                   <%--padding-left: -1px; margin-left: -30px">--%>
                                    <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                         <%--width="304" height="236" style="max-width: 115%">--%>
                                <%--</div>--%>
                                <%--<div class="col-md-6 col-sm-6">--%>
                                    <%--smit--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-3 col-sm-3"--%>
                                 <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                <%--padding-left: -1px; margin-left: -30px">--%>
                                    <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive" alt=""--%>
                                         <%--width="304" height="236" style="max-width: 115%">--%>
                                <%--</div>--%>
                                <%--<div class="col-md-6 col-sm-6">--%>
                                    <%--smit--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-3 col-sm-3"--%>
                                 <%--style=" background-color: #eee; margin-left:5px; width: 23%;">--%>
                                <%--<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd;--%>
                                <%--padding-left: -1px; margin-left: -30px">--%>
                                    <%--<img src="resources/AlbumArt/badrinath.jpg" class="img-responsive"--%>
                                         <%--alt="" width="304" height="236" style="max-width: 115%">--%>
                                <%--</div>--%>
                                <%--<div class="col-md-6 col-sm-6">--%>
                                    <%--smit--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
   </div>
</section>


<%@ include file="./uiFrameFooter.jsp" %>
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

<%--for glypicon--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--JS file for inserting user--%>
<script type="text/javascript" src="resources/slick/slick.js"></script>
<script src="/resources/app/js/test.js"></script>
<script src="/resources/app/js/dashboard.js"></script>
</body>
</html>