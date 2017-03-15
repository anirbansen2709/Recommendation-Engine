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
  .content-wrapper > h3,/* .content-wrapper > .content-heading*/ {
    margin-bottom: 4px;
    padding: 4px;
  }
  .wrapper > section{
    top: -20px;
  }

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
          <form role="form" class="form-inline" id="formid">

            <div class="fixedscroll">

                <table class="table table-bordered table-striped table-hover" id="songs_table">
                    <thead>
                    <tr>
                        <th style="width: 10%">Movie Id</th>
                        <th>Movie Name</th>
                        <th>Avg Rate</th>
                        <th>Rate</th>
                    </tr>
                    </thead>
                    <tbody style="cursor: pointer"></tbody>
                </table>
                <button type="button" class="btn btn-primary btn-lg pull-right" id="saveRatings">Save</button>

            </div>



          </form>
        </div>
      </div>
    </div>

    <!-- END panel-->
    <div class="row">
      <div class="panel panel-default">


      </div>
    </div>
</section>

<html>
<head>
    <title></title>
</head>
<body>
  <div id="rateYo"></div>



</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="./resources/app/js/rateSongs.js"></script>
<%@ include file="./uiFrameFooter.jsp" %>
