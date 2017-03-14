<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>


<link rel="stylesheet" href="./resources/buttonAddSub/css/style.css">
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
<script src="./resources/buttonAddSub/js/incrementing.js"></script>


<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./resources/css/dataTables.bootstrap.min.css">
<%--ajax--%>
<script src="./resources/js/jquery-1.12.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>


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

    ul.ColVis_collection {
        background-color: #fafafa;;
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
                        <li role="presentation" id="systemUpTime" class="active">
                            <a href="#home" aria-controls="home" role="tab" data-toggle="tab" class="bb0">
                                <em class="fa fa-clock-o fa-fw fa-lg"></em>System Up Time Test<b id="uptime3hrs1day">  (default: 3hrs in last 1 Day)</b></a>
                        </li>
                        <li role="presentation" id="process">
                            <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="bb0">
                                <em class="fa fa-spinner fa-spin fa-fw fa-lg"></em>Running Process Test</a>
                        </li>
                        <li role="presentation" id="softwareUpdate">
                            <a href="#profile2" aria-controls="profile" role="tab" data-toggle="tab" class="bb0">
                                <em class="fa fa-chain-broken fa-fw fa-lg"></em>Software Update Test</a>
                        </li>
                        <li role="presentation" id="duplicateIp">
                            <a href="#profile3" aria-controls="profile" role="tab" data-toggle="tab" class="bb0">
                                <em class="fa fa-copy fa-fw fa-lg"></em>Duplicate IP Test</a>
                        </li>
                    </ul>
                    <!-- Tab panes-->
                    <div class="tab-content p0 bg-white">
                        <div id="home" role="tabpanel" class="tab-pane active">
                            <!-- START list group-->
                            <div class="tableHide" id="style41">
                                <table id="exampleSystemUpTime" class="table table-striped table-bordered"
                                       cellspacing="0"
                                       width="100%">
                                    <thead id="theadSystemUpTime"></thead>
                                    <tbody id="tbodySystemUpTime"></tbody>
                                </table>
                            </div>
                            <!-- END list group-->
                        </div>
                        <div id="profile" role="tabpanel" class="tab-pane">
                            <!-- START list group-->
                            <div class="tableHide" id="style42">
                                <table id="exampleProcess" class="table table-striped table-bordered" cellspacing="0"
                                       width="100%">
                                    <thead id="theadProcess"></thead>
                                    <tbody id="tbodyProcess"></tbody>
                                </table>
                            </div>

                            <!-- END list group-->
                        </div>
                        <div id="profile2" role="tabpanel" class="tab-pane">
                            <!-- START list group-->
                            <div class="tableHide " id="style43">
                                <table id="exampleSoftwareUpdate" class="table table-striped table-bordered"
                                       cellspacing="0"
                                       width="100%">
                                    <thead id="theadSoftwareUpdate"></thead>
                                    <tbody id="tbodySoftwareUpdate"></tbody>
                                </table>
                            </div>

                            <!-- END list group-->
                        </div>
                        <div id="profile3" role="tabpanel" class="tab-pane">
                            <!-- START list group-->
                            <div class="tableHide" id="style44">
                                <table id="exampleDuplicateIp" class="table table-striped table-bordered"
                                       cellspacing="0"
                                       width="100%">
                                    <thead id="theadDuplicateIp"></thead>
                                    <tbody id="tbodyDuplicateIp"></tbody>
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

</body>

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
<div class="modal fade" id="timeRangeModel" role="dialog">
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
                    <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Set System up Time limit</span>
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
                                    <input type="text" id="bytes" value="mins">
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
<div class="modal fade" id="timeAndDuration" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="col-xs-9">
                    <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>View activities of last</span>
                    </h4>
                </div>
             <%--   <div class="col-xs-2">
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
                    <div class="text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Enter time duration:</span></div>
                    <div class="row row-table">
                        <div class="col-xs-12">
                            <div class="col-xs-5">
                                <div class="numbers-row">
                                    <input type="text" id="frequency2" value="0">
                                </div>

                            </div>
                            <div class="col-xs-5">
                                <div class="numbers-row">
                                    <input type="text" id="duration2" value="days">
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
                    <div class="text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Enter system Up Time:</span></div>

                    <div class="row row-table">
                        <div class="col-xs-12">
                            <div class="col-xs-5">
                                <div class="numbers-row">
                                    <input type="text" id="data2" value="0">
                                </div>
                            </div>
                            <div class="col-xs-5">
                                <div class="numbers-row">
                                    <input type="text" id="bytes2" value="hours">
                                </div>

                            </div>
                            <div class="col-xs-2">
                                <button type="button" class="btn btn-pill-right btn-info" id="SubmitTimeAndDuration">Submit</button>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- END date widget    -->


            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="processModel" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content" style="width: 450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="col-xs-9">
                    <h4 class="modal-title text-white"><span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Enter process name to test</span>
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
                    }
/*
                    input[type=text] {
                        width: 50%;
                    }*/
                    </style>
<%--
                    <div class="row row-table">
                        <div class="col-xs-12">
                            &lt;%&ndash;<div class="col-xs-2"><img src="./resources/images/Chrome.png" height="50px" width="50"
                                                       id="Chrome">
                            </div>
                            <div class="col-xs-2"><img src="./resources/images/utorrent.png" height="50px" width="50"
                                                       id="utorrent">
                            </div>
                            <div class="col-xs-2"><img src="./resources/images/java.PNG" height="50px" width="50"
                                                       id="java">
                            </div>
                            <div class="col-xs-2"><img src="./resources/images/SkypeHost.png" height="50px" width="50"
                                                       id="SkypeHost">
                            </div>
                            <div class="col-xs-2"><img src="./resources/images/snmp.png" height="50px" width="70"
                                                       id="snmp">
                            </div>
                            <div class="col-xs-2"><img src="./resources/images/vlc.jpg" height="50px" width="50"
                                                       id="vlc">
                            </div>&ndash;%&gt;

                        </div>
                    </div>
--%>
                    <br>

                    <div class="row row-table">
                        <div class="col-xs-12">
                            <div class="col-xs-2 text-white">
                                Process:
                            </div>
                                <div class="col-xs-6">
                                <select id="bob" style="width:150px; height:30px;">
                                    <option value="chrome.exe">Chrome</option>
                                    <option value="uTorrent.exe">uTorrent</option>
                                    <option value="java.exe">Java</option>
                                    <option value="SkypeHost.exe">Skype</option>
                                    <option value="snmp.exe">SNMP</option>
                                    <option value="vlc.exe">VLC</option>
                                    <option value="mysqld.exe">SQL</option>
                                    <option value="idea.exe">IDEA</option>
                                    <option value="system">OS</option>
                                </select>
                            </div>
                                <div class="col-xs-4">
                                <button type="button" class="btn btn-pill-right btn-info" id="buttonProcess">Submit
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

<%--imports for date time picker--%>
<%--<style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>--%>
<link rel="stylesheet" href="./resources/datetimepicker/kendo.common.min.css"/>
<%--<script src="./resources/datetimepicker/jquery.min.js"></script>--%>
<script src="./resources/datetimepicker/kendo.all.min.js"></script>

<link rel="stylesheet" href="./resources/datetimepicker/kendo.black.min.css"/>

<!-- DATATABLES-->
<script src="./resources/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/vendor/datatables-colvis/js/dataTables.colVis.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js"></script>

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
        console.log(deviceIdentifier);

        if (device == "systemUpTime") {
            $("#timeAndDuration").modal("show");

        }
        else if (device == "process") {
            $("#processModel").modal("show");
        }
        else if (device == "softwareUpdate") {
            $("#timeRangeModel").modal("show");
        }
        else if (device == "duplicateIp") {
            $("#timeRangeModel").modal("show");
        }
    }
    function SubmitTimeAndDuration() {
        var frequency, duration;
        frequency = $("#frequency2").val();
        duration = $("#duration2").val();
        var data, bytes;
        data = $("#data2").val();
        bytes = $("#bytes2").val();
        console.log(data+bytes+deviceIdentifier+frequency+duration);
        $.ajax({
            url: "submitTimeAndDuration",
            data:"Data=" + data +"&bytes=" + bytes +"&deviceId=" + deviceIdentifier +"&frequency=" + frequency + "&duration=" + duration,
            success: function (response) {
                $("#timeAndDuration").modal("hide");
                response = jQuery.parseJSON(response);
                if (deviceIdentifier == "systemUpTime") {
                    createSystemUpTimeBody(response);
                }
             /*   else if (deviceIdentifier == "process") {
                    createProcessBody(response);
                }
             */   else if (deviceIdentifier == "softwareUpdate") {
                    createSoftwareUpdateBody(response);
                }
              /*  else if (deviceIdentifier == "duplicateIp") {
                    createDuplicateIpBody(response);
                }*/
            },
            error: function (e) {
                alert('Error: submitTimeAndDuration ' + e);
            }
        });
    }


    function submitFrequencyDuration() {
        var frequency, duration;
        frequency = $("#frequency").val();
        duration = $("#duration").val();
        console.log("submitFrequencyDuration sending to TestIndexController:-", frequency, duration);
        $.ajax({
            url: "submitFrequencyDuration",
            data: "frequency=" + frequency + "&duration=" + duration + "&deviceId=" + deviceIdentifier,
            success: function (response) {
                $("#timeRangeModel").modal("hide");
                response = jQuery.parseJSON(response);
                console.log(response);
                if (deviceIdentifier == "systemUpTime") {
                    createSystemUpTimeBody(response);
                }
                else if (deviceIdentifier == "process") {
                    createProcessBody(response);
                }
                else if (deviceIdentifier == "softwareUpdate") {
                    createSoftwareUpdateBody(response);
                }
                else if (deviceIdentifier == "duplicateIp") {
                    createDuplicateIpBody(response);
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function submitCustomize() {
        $("#timeRangeModel").modal("hide");
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
                if (deviceIdentifier == "systemUpTime") {
                    createSystemUpTimeBody(response);
                }
                else if (deviceIdentifier == "process") {
                    createProcessBody(response);
                }
                else if (deviceIdentifier == "softwareUpdate") {
                    createSoftwareUpdateBody(response);
                }
                else if (deviceIdentifier == "duplicateIp") {
                    createDuplicateIpBody(response);
                }

            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function submitLimit() {
        var data, bytes;
        data = $("#data").val();
        bytes = $("#bytes").val();
        console.log("inside Submit limit", data, bytes);
        $.ajax({
            url: "submitTimeLimit",
            data: "Data=" + data + "&bytes=" + bytes,
            success: function (response) {
                $("#limitModel").modal("hide");
                $("#timeRangeModel").modal("show");
            }
            ,
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }
    function submitProcess() {
        var data;
        data = document.getElementById("bob").value;
        console.log("inside Submit process", data);
        $.ajax({
            url: "submitProcess",
            data: "processName=" + data,
            success: function (response) {
                $("#processModel").modal("hide");
                $("#timeRangeModel").modal("show");
            }
            ,
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
    $("#buttonProcess").click(function (e) {
        console.log("submit process");
        submitProcess();
    });
    $("#SubmitTimeAndDuration").click(function (e) {
        SubmitTimeAndDuration();
    });

/*

    $("#Chrome").click(function (e) {$("#processName").val("chrome.exe");});
    $("#idea").click(function (e) {$("#processName").val("idea.exe");});
    $("#java").click(function (e) {$("#processName").val("java.exe");});
    $("#mysqld").click(function (e) {$("#processName").val("mysqld.exe");});
    $("#OS").click(function (e) {$("#processName").val("system");});
    $("#SkypeHost").click(function (e) {$("#processName").val("SkypeHost.exe");});
    $("#snmp").click(function (e) {$("#processName").val("snmp.exe");});
    $("#utorrent").click(function (e) {$("#processName").val("uTorrent.exe");});
    $("#vlc").click(function (e) {$("#processName").val("vlc.exe");});

*/

    $(document).ready(function() {
        var frequency, duration;
        frequency = 3;
        duration = "hours";
        var data, bytes;
        data = 1;
        bytes = "days";
        deviceIdentifier = "systemUpTime";
        console.log(data+bytes+deviceIdentifier+frequency+duration);
        $.ajax({
            url: "submitTimeAndDuration",
            data:"Data=" + data +"&bytes=" + bytes +"&deviceId=" + deviceIdentifier +"&frequency=" + frequency + "&duration=" + duration,
            success: function (response) {
                $("#timeAndDuration").modal("hide");
                response = jQuery.parseJSON(response);
                if (deviceIdentifier == "systemUpTime") {
                    createSystemUpTimeBody(response);
                }
                /*   else if (deviceIdentifier == "process") {
                 createProcessBody(response);
                 }
                 */   else if (deviceIdentifier == "softwareUpdate") {
                    createSoftwareUpdateBody(response);
                }
                else if (deviceIdentifier == "duplicateIp") {
                    createDuplicateIpBody(response);
                }
            },
            error: function (e) {
                alert('Error: submitTimeAndDuration ' + e);
            }
        });
    });
</SCRIPT>
<%--searchabledropdown--%>
<style>
    .sddl{display:inline-block}
    .sddl-label{border: 1px solid #aaa; padding: 5px}
    .sddl-label:after{border:1px solid #aaa;width:25px; height: 25px;}
    .sddl-search{width: 100%; border:0px; border-bottom: 1px; padding: 5px}
    .sddl-options{position: absolute; list-style:none; padding:0; margin:0px; max-height: 200px; overflow-y: scroll; overflow-x: hidden; border: 1px solid #aaa}
    .sddl-options li:first-child{padding:0px;margin-top: -3px; outline:0px; margin-left:-1px}
    .sddl-options li{background: #fff; padding: 5px}
    .sddl-options li:hover{background: #efefef}
</style>
<%--
&lt;%&ndash;searchabledropdown&ndash;%&gt;
<script>
    $(document).load(function() {

        $(function () {
            $.fn.sddl = function (opts) {
                $(this).each(function () {
                    $(this).after("<div class='sddl'><div class='sddl-label'></div><ul class='sddl-options'></ul></div>");
                    var dd = $(this),
                            dopts = dd.children(),
                            sddl = $('.sddl'),
                            sddlOptions = $('.sddl-options'),
                            label = $('.sddl-label');

                    //  initial setup
                    sddlOptions.append("<li><input type='text' class='sddl-search' placeholder='Search' /></li>");
                    dopts.each(function () {
                        sddlOptions.append("<li>" + this.innerHTML + "</li>");
                    });
                    var children = sddlOptions.children().not(":eq(0)");
                    label.text(dopts[0].innerHTML);
                    dd.hide();

                    //  width and position
                    sddl.width(dd.width() + 'px');
                    sddlOptions.css({
                        width: label.width() + 'px',
                        left: label.offset().left + 'px',
                        top: (label.offset().top + label.outerHeight()) + 'px'
                    });
                    sddlOptions.hide();

                    //  Events
                    label.click(function () {
                        sddlOptions.show()
                    });
                    children.click(function () {
                        dd.val(this.innerHTML);
                        label.text($(this).text());
                        sddlOptions.hide();
                        children.show();
                    });
                    $('.sddl-search').keyup(function () {
                        var term = $(this).val().toLowerCase();
                        children.each(function () {
                            if ($(this).text().toLowerCase().indexOf(term) == -1) {
                                $(this).hide();
                            } else {
                                $(this).show();
                            }
                        });
                    });

                });
            };
        });

        $('#bob').sddl();
    });
</script>--%>
</html>