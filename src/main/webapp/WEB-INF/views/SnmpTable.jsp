<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
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
    #spinner{
        display: none;
    }
    table.table-bordered.dataTable {
        border-collapse: separate !important;
        border: 2px solid #C1C1C1;
    }
    ul.ColVis_collection {
        background-color:  #fafafa;;
    }

</style>

<script>
    //ajax call
    function doAjaxPost(map) {

        var mapName = map;
        $(".hide").css({"pointer-events": "none", "opacity": "0.2"});
        $("#spinner").show();
        $(".tableHide").hide();
        $.ajax({
            type: "Get",
            url: "hello",
            data: "&mapName=" + mapName,
            success: function (response) {
//                   alert(response);
                $("#spinner").hide();
                $(".tableHide").show();
                $(".hide").css({"pointer-events": "auto", "opacity": "1"});
                if (response == "ipInvalid")
                    $("#ipInvalid").modal("show");
                else {
                    response = jQuery.parseJSON(response);
                    if (mapName == "processMap") {
                        createDiv1(response);

                    }
                    else if (mapName == "SWInstalledMap") {
                        createDiv2(response);

                    }
                    else if (mapName == "storageMap") {
                        createDiv3(response);

                    }
                    else if (mapName == "deviceMap") {
                        createDiv4(response);

                    }
                    else if (mapName == "interfaceMap") {
                        createDiv5(response);

                    }
                    else if (mapName == "systemMap") {
                        createDiv6(response);
                    }
                }
            },
            error: function (e) {
                $("#spinner").hide();
                $(".tableHide").show();
                $(".hide").css({"pointer-events": "auto", "opacity": "1"});


            }
        });
    }
    //});

</script>
<!-- Main section-->
<section>
    <!-- Page content-->
    <div class="content-wrapper">
        <!-- START widgets box-->

        <div class="row">

            <div class="col-lg-12 text-center v-center hide">
                <div id="panelDemo14" class="panel panel-default">
                    <div class="panel-heading">Live View</div>
                    <div class="panel-body">
                        <div role="tabpanel">
                            <!-- Nav tabs-->
                            <ul role="tablist" class="nav nav-tabs">
                                <li role="presentation" id="ProcessesTab" class="active"><a href="#Processes" aria-controls="Processes" role="tab" data-toggle="tab">Processes</a>
                                </li>
                                <li role="presentation" id="SW_InstalledTab"><a href="#SW_installed" aria-controls="SW_installed" role="tab" data-toggle="tab">SW installed</a>
                                </li>
                                <li role="presentation" id="StorageTab"><a href="#Storage" aria-controls="Storage" role="tab" data-toggle="tab">Storage</a>
                                </li>
                                <li role="presentation" id="DevicesTab"><a href="#Device" aria-controls="Device" role="tab" data-toggle="tab">Device</a>
                                </li>
                                <li role="presentation" id="InterfaceTab"><a href="#Interface" aria-controls="Interface" role="tab" data-toggle="tab">Interface</a>
                                </li>
                                <li role="presentation" id="SystemTab"><a href="#System" aria-controls="System" role="tab" data-toggle="tab">System</a>
                                </li>
                            </ul>
                            <!-- Tab panes-->
                            <div class="tab-content">

                                <div id="Processes" role="tabpanel" class="tab-pane active">
                                    <div id="div1" class ="tableHide ">
                                        <table id="datatableProcesses" class="table table-striped table-hover table-bordered" width="100%">
                                            <thead id="thead"></thead>
                                            <tbody id="tBody"></tbody>
                                        </table>

                                    </div>
                                </div>

                                <div id="SW_installed" role="tabpanel" class="tab-pane">
                                    <div id="div2" class ="tableHide ">
                                        <table id="datatableSW_installed" class="table table-striped table-hover table-bordered" width="100%">
                                            <thead id="thead2"></thead>
                                            <tbody id="tBody2"></tbody>
                                        </table>
                                    </div>
                                </div>

                                <div id="Storage" role="tabpanel" class="tab-pane">
                                    <div id="div3" class ="tableHide ">
                                        <table id="datatableStorage" class="table table-striped table-hover table-bordered" width="100%">
                                            <thead id="thead3"></thead>
                                            <tbody id="tBody3"></tbody>
                                        </table>
                                    </div>
                                </div>

                                <div id="Device" role="tabpanel" class="tab-pane ">
                                    <div id="div4" class ="tableHide ">
                                        <table id="datatableDevice" class="table table-striped table-hover table-bordered" width="100%">
                                            <thead id="thead4"></thead>
                                            <tbody id="tBody4"></tbody>
                                        </table>
                                    </div>
                                </div>

                                <div id="Interface" role="tabpanel" class="tab-pane">
                                    <div id="div5" class ="tableHide ">
                                        <table id="datatableInterface" class="table table-striped table-hover table-bordered" width="100%">
                                            <thead id="thead5"></thead>
                                            <tbody id="tBody5"></tbody>
                                        </table>
                                    </div>
                                </div>

                                <div id="System" role="tabpanel" class="tab-pane">
                                    <div id="div6" class ="tableHide ">
                                        <table id="datatableSystem" class="table table-striped table-hover table-bordered"  width="100%">
                                            <thead id="thead6"></thead>
                                            <tbody id="tBody6"></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


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
<!-- CLASSY LOADER-->
<script src="./resources/vendor/jquery-classyloader/js/jquery.classyloader.min.js"></script>
<!-- MOMENT JS-->
<script src="./resources/vendor/moment/min/moment-with-locales.min.js"></script>
<!-- SKYCONS-->
<script src="./resources/vendor/skycons/skycons.js"></script>
<!-- DEMO-->
<script src="./resources/app/js/demo/demo-flot.js"></script>

<!-- DATATABLES-->
<script src="./resources/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/vendor/datatables-colvis/js/dataTables.colVis.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js"></script>

<!-- =============== APP SCRIPTS ===============-->
<script src="./resources/app/js/app.js"></script>


<script type="text/javascript" src="./resources/js/createProcessMap.js"></script>
<div class="modal fade" id="ipInvalid" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"> Error </h4>
            </div>
            <div class="modal-body">
                <p>IP with this community is not in the network </p>
            </div>
        </div>

    </div>
</div>


</body>
<script>
    $("#ProcessesTab").click(function (e){doAjaxPost('processMap');});
    $("#SW_InstalledTab").click(function (e){doAjaxPost('SWInstalledMap');});
    $("#StorageTab").click(function (e){doAjaxPost('storageMap');});
    $("#DevicesTab").click(function (e){doAjaxPost('deviceMap');});
    $("#InterfaceTab").click(function (e){doAjaxPost('interfaceMap');});
    $("#SystemTab").click(function (e){doAjaxPost('systemMap');});

    $(document).ready(function() {
        doAjaxPost('processMap');
    });
</script>
</html>