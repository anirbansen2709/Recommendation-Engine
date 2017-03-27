
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="Bootstrap Admin App + jQuery">
<meta name="keywords" content="app, responsive, jquery, bootstrap, dashboard, admin">
<title>Listen Me</title>
<!-- =============== VENDOR STYLES ===============-->
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
</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>

<div class="wrapper">
  <jsp:include page="layout/header.jsp"/>
  <jsp:include page="layout/menu.jsp"/>
<section  style="margin-bottom: 10px !important;">


    <div class="content-wrapper">
      <%--<h3>Device management--%>
      <%--<small>Add new SNMP devices</small>--%>
      <%--</h3>--%>
      <!-- START panel-->
      <div class="row">
        <div class="panel panel-default">
          <div class="panel-heading" style="background-color: #003153 !important; color: white !important;">
            <b>History</b></div>
          <div class="panel-body">
            <form role="form" class="form-inline" id="formid">
              <div class="fixedscroll">
                <table class="table table-bordered table-striped table-hover" id="history_table">
                  <thead>
                  <tr>
                    <th style="text-align: center;background-color: #007BA7; color: white; width: 15%;">MovieId
                    </th>
                    <th style="text-align: center;background-color: #007BA7; color: white; width: 25%;">Movie
                      Name
                    </th>
                    <th style="text-align: center;background-color: #007BA7; color: white; width: 30%;">Genres
                    </th>
                    <th style="text-align: center;background-color: #007BA7; color: white;width: 15%;">Your Rating</th>
                    <th style="text-align: center;background-color: #007BA7; color: white;width: 15%;">Timestamp</th>
                  </tr>
                  </thead>
                  <tbody style="cursor: pointer; padding: 0 !important;"></tbody>
                </table>
                <button type="button" class="btn btn-info btn-lg pull-right"
                        id="saveRatings">Save
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>


</section>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<%@ include file="./uiFrameFooter.jsp" %>

<div class="modal fade" id="myModal" role="dialog" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <%--<button type="button">&times;</button>--%>
        <h4 id="modalHeaderContent"></h4>
      </div>
      <div class="modal-body">
        <p>
          <%--<div id = "modalText" >--%>
          <span id="modalBodyContent"></span>
          <%--</div>--%>
          <span id="modalBodyContent"></span>

        </p>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="error-close" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="success-close">Close</button>
        <button type="button" class="btn btn-primary" id="redirect-btn">Ok</button>
      </div>
    </div>

  </div>
</div>
<script src="resources/app/js/history.js"></script>
<script type="text/javascript" src="resources/slick/slick.js"></script>
<script src="/resources/app/js/test.js"></script>
