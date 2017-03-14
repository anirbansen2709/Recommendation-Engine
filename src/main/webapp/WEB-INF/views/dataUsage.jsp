<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<%--

<style>.modal-dialog {
  width: auto;
  margin: 202px auto;
}</style>
--%>

<link rel="stylesheet" href="./resources/buttonAddSub/css/style.css">
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
<script src="./resources/buttonAddSub/js/incrementing.js"></script>


<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./resources/css/dataTables.bootstrap.min.css">
<%--ajax--%>
<script src="./resources/js/jquery-1.12.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>


<!-- DATATABLES-->
<script src="./resources/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/vendor/datatables-colvis/js/dataTables.colVis.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js"></script>



<style>

  div.DTTT {
    margin-bottom: 0.5em;
    float: right;
  }

  div.dataTables_wrapper {
    clear: both;
  }

  table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
    opacity: 0;
  }

  .sorting, .sorting_asc, .sorting_desc {
    background: url("./resources/images/sort_both.png") no-repeat scroll right center transparent;
  }

  .sk-cube-grid {
    width: 70px;
    height: 70px;
    margin: 100px auto;
  }

  .sk-cube-grid .sk-cube {
    width: 33%;
    height: 33%;
    background-color: #1537bf;
    float: left;
    -webkit-animation: sk-cubeGridScaleDelay 1.3s infinite ease-in-out;
    animation: sk-cubeGridScaleDelay 1.3s infinite ease-in-out;
  }

  .sk-cube-grid .sk-cube1 {
    -webkit-animation-delay: 0.2s;
    animation-delay: 0.2s;
  }

  .sk-cube-grid .sk-cube2 {
    -webkit-animation-delay: 0.3s;
    animation-delay: 0.3s;
  }

  .sk-cube-grid .sk-cube3 {
    -webkit-animation-delay: 0.4s;
    animation-delay: 0.4s;
  }

  .sk-cube-grid .sk-cube4 {
    -webkit-animation-delay: 0.1s;
    animation-delay: 0.1s;
  }

  .sk-cube-grid .sk-cube5 {
    -webkit-animation-delay: 0.2s;
    animation-delay: 0.2s;
  }

  .sk-cube-grid .sk-cube6 {
    -webkit-animation-delay: 0.3s;
    animation-delay: 0.3s;
  }

  .sk-cube-grid .sk-cube7 {
    -webkit-animation-delay: 0s;
    animation-delay: 0s;
  }

  .sk-cube-grid .sk-cube8 {
    -webkit-animation-delay: 0.1s;
    animation-delay: 0.1s;
  }

  .sk-cube-grid .sk-cube9 {
    -webkit-animation-delay: 0.2s;
    animation-delay: 0.2s;
  }

  @-webkit-keyframes sk-cubeGridScaleDelay {
    0%, 70%, 100% {
      -webkit-transform: scale3D(1, 1, 1);
      transform: scale3D(1, 1, 1);
    }
    35% {
      -webkit-transform: scale3D(0, 0, 1);
      transform: scale3D(0, 0, 1);
    }
  }

  @keyframes sk-cubeGridScaleDelay {
    0%, 70%, 100% {
      -webkit-transform: scale3D(1, 1, 1);
      transform: scale3D(1, 1, 1);
    }
    35% {
      -webkit-transform: scale3D(0, 0, 1);
      transform: scale3D(0, 0, 1);
    }
  }

  #spinner {
    display: none;
  }

  table.table-bordered.dataTable {
    border-collapse: separate !important;
    border: 2px solid #C1C1C1;
  }

</style>

<!-- Main section-->
<section>
  <!-- Page content-->

  <div class="content-wrapper">
    <!-- START widgets box-->
    <div class="row">
      <div class="col-lg-12">
        <!-- START panel tab-->
        <div role="tabpanel" class="panel panel-transparent">
          <!-- Nav tabs-->
          <ul role="tablist" class="nav nav-tabs nav-justified">
            <li role="presentation" id="download" class="active">
              <a href="#home" aria-controls="home" role="tab" data-toggle="tab" class="bb0">
                <em class="fa fa-cloud-download fa-fw fa-lg"></em>Download Test <b id="limit100MB">  (default: 100MB download in last 24hrs)</b></a>
            </li>
            <li role="presentation" id="upload">
              <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="bb0">
                <em class="fa fa-upload fa-fw fa-lg"></em>Upload Test</a>
            </li>
          </ul>
          <!-- Tab panes-->
          <div class="tab-content p0 bg-white">
            <div id="home" role="tabpanel" class="tab-pane active">
              <!-- START list group-->
              <div id="style31" class="tableHide ">
                <table id="exampleDownload" class="table table-striped table-bordered" cellspacing="0"
                       width="100%">
                  <thead id="theadDownload"></thead>
                  <tbody id="tbodyDownload"></tbody>
                </table>
              </div>
              <!-- END list group-->
            </div>
            <div id="profile" role="tabpanel" class="tab-pane">
              <!-- START list group-->
              <div id="style32" class="tableHide ">
                <table id="exampleUpload" class="table table-striped table-bordered" cellspacing="0"
                       width="100%">
                  <thead id="theadUpload"></thead>
                  <tbody id="tbodyUpload"></tbody>
                </table>
              </div>

              <!-- END list group-->
            </div>
          </div>
        </div>
        <!-- END panel tab-->
      </div>

    </div>

  </div>
</section>
<footer>
  <span>@Mahaan:2016</span>
</footer>
</div>
<!-- =============== VENDOR SCRIPTS ===============-->
<!-- MODERNIZR-->
<script src="./resources/vendor/modernizr/modernizr.js"></script>
<!-- JQUERY-->
<%--<script src="./resources/vendor/jquery/dist/jquery.js"></script>--%>
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
<!-- =============== APP SCRIPTS ===============-->
<script src="./resources/app/js/app.js"></script>


<%--SCRIPT FOR CREATING TEST DATA TABLES--%>
<script src="./resources/js/tests.js"></script>


<div class="modal fade" id="customizeTimeModel" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-12">
          <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Select time range:</span>
          </h4>
        </div>
      </div>
      <div class="modal-body">
        <!-- START date widget-->
        <div class="widget" id="time">
          <style>.modal-content {
            background-color: #4D4D4D;
          }

          .widget {
            margin-bottom: 0px;
          }</style>
          <div class="row row-table">
            <div class="col-xs-6">
              <div id="example">
                <div class="demo-section k-content row">

                  <h4>Start date</h4>
                  <input id="start" style="width: 100%;"/>

                  <h4 style="margin-top: 2em;">End date</h4>
                  <input id="end" style="width: 100%;"/>
                  <button type="button" class="btn btn-pill-right btn-info"
                          id="buttonSubmitStartEndDate">Submit
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- END date widget    -->


      </div>
    </div>

  </div>
</div>
<div class="modal fade" id="DataLimitAndDuration" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-9">
          <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>View activities of last</span>
          </h4>
        </div>
<%--
        <div class="col-xs-2">
          <button type="button" class="btn btn-pill-right btn-warning" id="buttonCustomize">customize</button>
        </div>
--%>

      </div>
      <div class="modal-body">

        <!-- START date widget-->
        <div class="widget" id="timeset">
          <style>.modal-content {
            background-color: #4D4D4D;
          }

          .widget {
            margin-bottom: 0px;
          }</style>
          <div class="text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Enter Time Duration:</span></div>
          <div class="row row-table">
            <div class="col-xs-12">
              <div class="col-xs-5">
                <div class="numbers-row">
                  <input type="text" id="frequency" value="0">
                </div>

              </div>
              <div class="col-xs-5">
                <div class="numbers-row">
                  <input type="text" id="duration" value="hours">
                </div>

              </div>
              <div class="col-xs-2">
              </div>

            </div>
          </div>
        </div>
        <!-- END date widget    -->
        <br>
        <!-- START date widget-->
        <div class="widget">
          <div class="text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Enter Data Limit:</span></div>

          <div class="row row-table">
            <div class="col-xs-12">
              <div class="col-xs-5">
                <div class="numbers-row">
                  <input type="text" id="data" value="0">
                </div>
              </div>
              <div class="col-xs-5">
                <div class="numbers-row">
                  <input type="text" id="bytes" value="KB">
                </div>

              </div>
              <div class="col-xs-2">
                <button type="button" class="btn btn-pill-right btn-info" id="SubmitDataLimitAndDuration">Submit</button>
              </div>

            </div>
          </div>
        </div>
        <!-- END date widget    -->


      </div>
    </div>

  </div>
</div>
</body>

<%--imports for date time picker--%>
<%--<style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>--%>
<link rel="stylesheet" href="./resources/datetimepicker/kendo.common.min.css"/>
<%--<script src="./resources/datetimepicker/jquery.min.js"></script>--%>
<script src="./resources/datetimepicker/kendo.all.min.js"></script>
<link rel="stylesheet" href="./resources/datetimepicker/kendo.black.min.css"/>


<script>
  <%--script for date time picker--%>

  $(document).ready(function () {
    function startChange() {
      var startDate = start.value(),
              endDate = end.value();

      if (startDate) {
        startDate = new Date(startDate);
        startDate.setDate(startDate.getDate());
        end.min(startDate);
      } else if (endDate) {
        start.max(new Date(endDate));
      } else {
        endDate = new Date();
        start.max(endDate);
        end.min(endDate);
      }
    }

    function endChange() {
      var endDate = end.value(),
              startDate = start.value();

      if (endDate) {
        endDate = new Date(endDate);
        endDate.setDate(endDate.getDate());
        start.max(endDate);
      } else if (startDate) {
        end.min(new Date(startDate));
      } else {
        endDate = new Date();
        start.max(endDate);
        end.min(endDate);
      }
    }

    var today = kendo.date.today();

    var start = $("#start").kendoDateTimePicker({
      value: today,
      change: startChange,
      parseFormats: ["MM/dd/yyyy"]
    }).data("kendoDateTimePicker");

    var end = $("#end").kendoDateTimePicker({
      value: today,
      change: endChange,
      parseFormats: ["MM/dd/yyyy"]
    }).data("kendoDateTimePicker");

    start.max(end.value());
    end.min(start.value());
  });

  var deviceIdentifier;

  function test(device) {

    deviceIdentifier = device;

    if (device == "upload") {
      $("#DataLimitAndDuration").modal("show");

    }
    else if (device == "download") {
      $("#DataLimitAndDuration").modal("show");
    }
  }

  function submitCustomize() {
    $("##DataLimitAndDuration").modal("hide");
    $("#customizeTimeModel").modal("show");
  }

  function submitStartDateEndDate() {
    $("#customizeTimeModel").modal("hide");
    var startTime, endTime;
    startTime = $("#start").val();
    endTime = $("#end").val();
    console.log("@submitStartDateEndDate sending to TestIndexController:-", startTime, endTime);
    $.ajax({
      url: "submitStartDateEndDate",
      data: "startTime=" + startTime + "&endTime=" + endTime + "&deviceId=" + deviceIdentifier,
      success: function (response) {
        response = jQuery.parseJSON(response);
        console.log(response);
        if (deviceIdentifier == "download") {
          createDownloadBody(response);
        }
        else if (deviceIdentifier == "upload") {
          createUploadBody(response);
        }

      },
      error: function (e) {
        alert('Error: ' + e);
      }
    });
  }
  function SubmitDataLimitAndDuration() {
    var frequency, duration;
    frequency = $("#frequency").val();
    duration = $("#duration").val();
    var data, bytes;
    data = $("#data").val();
    bytes = $("#bytes").val();
    console.log(data+bytes+deviceIdentifier+frequency+duration);
    $.ajax({
      url: "submitDataLimitAndDuration",
      data:"Data=" + data +"&bytes=" + bytes +"&deviceId=" + deviceIdentifier +"&frequency=" + frequency + "&duration=" + duration,
      success: function (response) {
        $("#DataLimitAndDuration").modal("hide");
        response = jQuery.parseJSON(response);
        if (deviceIdentifier == "download") {
          createDownloadBody(response);
        }
        else if (deviceIdentifier == "upload") {
          createUploadBody(response);
        }
      },
      error: function (e) {
        alert('Error: submitDataLimitAndDuration ' + e);
      }
    });
  }

  $("#buttonCustomize").click(function (e) {
    submitCustomize();
  });
  $("#buttonSubmitStartEndDate").click(function (e) {
    submitStartDateEndDate();
  });
  $("#SubmitDataLimitAndDuration").click(function (e) {
    SubmitDataLimitAndDuration();
  });
  $(document).ready(function() {
    var frequency, duration;
    frequency = 1;
    duration = "days";
    var data, bytes;
    data = 100;
    bytes = "MB";
    deviceIdentifier = "download"
    $.ajax({
      url: "submitDataLimitAndDuration",
      data:"Data=" + data +"&bytes=" + bytes +"&deviceId=" + deviceIdentifier +"&frequency=" + frequency + "&duration=" + duration,
      success: function (response) {
        $("#DataLimitAndDuration").modal("hide");
        response = jQuery.parseJSON(response);
          createDownloadBody(response);
      },
      error: function (e) {
        alert('Error: submitDataLimitAndDuration ' + e);
      }
    });

  });

</SCRIPT>
</html>