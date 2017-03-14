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
    float: leanimation-delay: 0.1s;
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
   ul.ColVis_collection {  background-color: #fafafa;;  }


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
            <li role="presentation" id="pendrive" class="active">
              <a href="#home" aria-controls="home" role="tab" data-toggle="tab" class="bb0">
                <em class="fa fa-hdd-o fa-fw fa-lg"></em>Pendrive Test<b id="lastMonth">  (Last Month)</b></a>
            </li>
            <li role="presentation" id="printer">
              <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="bb0">
                <em class="fa fa-print fa-fw fa-lg"></em>Printer Test</a>
            </li>
          </ul>
          <!-- Tab panes-->
          <div class="tab-content p0 bg-white">
            <div id="home" role="tabpanel" class="tab-pane active">
              <!-- START list group-->
              <div id="style11" class="tableHide ">
                <table id="examplePendrive" class="table table-striped table-bordered" cellspacing="0"
                       width="100%">
                  <thead id="thead1"></thead>
                  <tbody id="tBody1"></tbody>
                </table>
              </div>
              <!-- END list group-->
            </div>
            <div id="profile" role="tabpanel" class="tab-pane">
              <!-- START list group-->
              <div id="style12" class="tableHide ">
                <table id="examplePrinter" class="table table-striped table-bordered" cellspacing="0"
                       width="100%">
                  <thead id="thead2"></thead>
                  <tbody id="tBody2"></tbody>
                </table>
              </div>

              <!-- END list group-->
            </div>
          </div>
        </div>
        <!-- END panel tab-->

      </div>
      <div class="col-lg-12 text-center v-center" id="spinner">
        <div  class="sk-cube-grid">
          <div class="sk-cube sk-cube1"></div>
          <div class="sk-cube sk-cube2"></div>
          <div class="sk-cube sk-cube3"></div>
          <div class="sk-cube sk-cube4"></div>
          <div class="sk-cube sk-cube5"></div>
          <div class="sk-cube sk-cube6"></div>
          <div class="sk-cube sk-cube7"></div>
          <div class="sk-cube sk-cube8"></div>
          <div class="sk-cube sk-cube9"></div>
        </div>
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


<div class="modal fade" id="customizeTime" role="dialog">
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
<div class="modal fade" id="timeRange" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-9">
          <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>View activities of last</span>
          </h4>
        </div>
        <div class="col-xs-2">
          <button type="button" class="btn btn-pill-right btn-warning" id="buttonCustomize">customize</button>
        </div>

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
                <button type="button" class="btn btn-pill-right btn-info"
                        id="buttonSubmitFrequencyDuration">Submit
                </button>
              </div>

            </div>
          </div>
        </div>
        <!-- END date widget    -->


      </div>
    </div>

  </div>
</div>
<div class="modal fade" id="limitModel" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-9">
          <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Set data size limit</span>
          </h4>
        </div>
      </div>
      <div class="modal-body">

        <!-- START date widget-->
        <div class="widget">
          <style>.modal-content {
            background-color: #4D4D4D;
          }

          .widget {
            margin-bottom: 0px;
          }</style>
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
                <button type="button" class="btn btn-pill-right btn-info" id="buttonLimit">Submit
                </button>
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

  function  test(device) {

    deviceIdentifier = device;

    if (device == "pendrive") {
      $("#timeRange").modal("show");
//            createDivPendrive();

    }
    else if(device == "printer") {
      $("#timeRange").modal("show");
//            createDivPrinter();
    }
  }

  function submitFrequencyDuration() {
    var frequency, duration;
    frequency = $("#frequency").val();
    duration = $("#duration").val();
    $("#spinner").show();
    console.log("submitFrequencyDuration sending to TestIndexController:-", frequency, duration);
    $.ajax({
      url: "submitFrequencyDuration",
      data: "frequency=" + frequency + "&duration=" + duration+ "&deviceId=" + deviceIdentifier,
      success: function (response) {
        $("#timeRange").modal("hide");
        $("#spinner").hide();

        response = jQuery.parseJSON(response);
        console.log(response);
        if(deviceIdentifier == "pendrive"){createPendriveBody(response);}
        else if(deviceIdentifier == "printer"){createPrinterBody(response);}
      },
      error: function (e) {
        alert('Error: ' + e);
      }
    });
  }

  function submitCustomize() {
    $("#timeRange").modal("hide");
    $("#customizeTime").modal("show");
  }

  function submitStartDateEndDate() {
    $("#customizeTime").modal("hide");
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
        if(deviceIdentifier == "pendrive"){createPendriveBody(response);}
        else if(deviceIdentifier == "printer"){createPrinterBody(response);}

      },
      error: function (e) {
        alert('Error: ' + e);
      }
    });
  }
  $("#buttonSubmitFrequencyDuration").click(function (e) {
    submitFrequencyDuration();
  });
  $("#buttonCustomize").click(function (e) {
    submitCustomize();
  });
  $("#buttonSubmitStartEndDate").click(function (e) {
    submitStartDateEndDate();
  });
  $("#buttonLimit").click(function (e) {
    submitLimit();
  });

  $(document).ready(function() {
    var frequency, duration;
      frequency = 2;
      duration = "months";
    deviceIdentifier = "pendrive";
    $("#spinner").show();
//    console.log("submitFrequencyDuration sending to TestIndexController:-", frequency, duration);
      $.ajax({
        url: "submitFrequencyDuration",
        data: "frequency=" + frequency + "&duration=" + duration+ "&deviceId=" + deviceIdentifier,
        success: function (response) {
          response = jQuery.parseJSON(response);
          console.log(response);

         createPendriveBody(response);
          $("#spinner").hide();
        },
        error: function (e) {
          alert('Error: ' + e);
        }
      });

  });

</SCRIPT>
</html>