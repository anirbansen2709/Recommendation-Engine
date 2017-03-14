<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 07-Mar-17
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="./uiFrame.jsp" %>
<style>
    .user-rating {
        direction: rtl;
        font-size: 20px;
        unicode-bidi: bidi-override;
        padding: 10px 30px;
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
        content:"ï€†";
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
        content:"\f006";
        /*padding-right: 5px;*/
    }
    .user-rating input:hover + span.star:before, .user-rating input:hover + span.star ~ span.star:before, .user-rating input:checked + span.star:before, .user-rating input:checked + span.star ~ span.star:before {
        color: #ffd100;
        content:"\f005";
    }

    .selected-rating{
        color: #ffd100;
        font-weight: bold;
        font-size: 3em;
    }
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<body>
<section>

    <div class="content-wrapper">
        <h3>Device management
            <small>Add new SNMP devices</small>
        </h3>
        <!-- START panel-->
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">Device Management</div>
                <div class="panel-body">
                    <form id="user-rating-form">
                        <span class="user-rating">
                        <input type="radio" name="rating" value="5"><span class="star"></span>
                        <input type="radio" name="rating"  value="4"><span class="star"></span>
                        <input type="radio" name="rating" value="3"><span class="star"></span>
                        <input type="radio" name="rating" value="2"><span class="star"></span>
                        <input type="radio" name="rating" value="1"><span class="star"></span>
                    </span>
                    </form>
                    <p style="margin-left: 20px">You have selected <span id="selected-rating"
                                                                         class="selected-rating">0</span> stars.</p>

                </div>
            </div>
        </div>

        <!-- END panel-->
        <div class="row">
            <div class="panel panel-default">


            </div>
        </div></div>
</section>
<%@ include file="./uiFrameFooter.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--for glypicon--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--JS file for inserting user--%>
<script src="/resources/app/js/test.js"></script>



</body>




</html>
