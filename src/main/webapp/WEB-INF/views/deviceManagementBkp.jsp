<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/22/2016
  Time: 9:52 AM
--%>
<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<%--<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>--%>
<%--<script type="text/javascript" src="resources/js/jquery-1.12.0.min.js"></script>--%>
<%--<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>--%>
<!-- Main section-->

<!-- DATATABLES-->
<link rel="stylesheet" href="./resources/vendor/datatables-colvis/css/dataTables.colVis.css">
<link rel="stylesheet" href="./resources/vendor/datatable-bootstrap/css/dataTables.bootstrap.css">
<section>
    <!-- Page content-->
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
                        <div class="form-group">
                            <label for="ip" class="sr-only">IP</label>
                            <input id="ip" type="text" placeholder="Enter IP" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="community" class="sr-only">Community</label>
                            <input id="community" type="text" placeholder="Enter Community" class="form-control">

                        </div>

                        <button type="button" class="btn btn-default" id="submit" onclick="doLoginPost();">ADD it!
                        </button>
                        <br>
                        <span style="color: #ff5140; display: none" id="ipSpan">*Please enter the correct ip    </span>
                        <span style="color: #ff5783; display: none" id="communitySpan">  &nbsp;&nbsp;   *Please enter the community</span>

                    </form>
                </div>
            </div>
        </div>
        <!-- END panel-->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of IPs stored in the Database
                    </div>
                    <div class="panel-body">
                        <table id="deviceTable" class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>IP</th>
                                <th>Community</th>
                                <th>Mac Address</th>
                                <th>See Live Data</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>
                                    <input type="text" name="filter_rendering_engine" placeholder="Filter IP"
                                           class="form-control input-sm datatable_input_col_search">
                                </th>
                                <th>
                                    <input type="text" name="filter_browser" placeholder="Filter Community"
                                           class="form-control input-sm datatable_input_col_search">
                                </th>
                                <th>
                                    <input type="text" name="filter_platform" placeholder="Filter Mac"
                                           class="form-control input-sm datatable_input_col_search">
                                </th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%--this will import footer and all the commom js--%>
<%@ include file="./uiFrameFooter.jsp" %>
<%--models--%>

<script>
    $(document).ready(function () {
        $.ajax({
            type: "Get",
            url: "loginAjax",
            data: "ip=" + "" + "&community=" + "",
            success: function (response) {
                console.log("  " + response);
                if (response != "duplicateDevice") {
                    response = jQuery.parseJSON(response);
                    createAccordion(response);
                    loadjs();
                }
                else {
                    console.log("no devices found in the db");
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });
    //  device management button functions
    function showLiveTable(ipGot, communityGot) {
        console.log(ipGot + communityGot);
        $.ajax({
            type: "Get",
            url: "table",
            data: "ip=" + ipGot + "&community=" + communityGot,
            success: function () {

                window.location.href = "./SnmpTable";

            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

    }

    function removeRow(ipGot) {
        console.log(ipGot);
        $.ajax({
            type: "Get",
            url: "deleteDevice",
            data: "ip=" + ipGot,
            success: function (response) {
                console.log(response);
                if (response != "noDevice") {
                    response = jQuery.parseJSON(response);
                    createAccordion(response);
                }
                else {
                    console.log("no devices found in the db");
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }


    function movetoNext(current, nextFieldID) {
        if (current.value.length >= current.maxLength) {
            document.getElementById(nextFieldID).focus();
        }
    }
    //ajax call
    function doLoginPost() {

        var ip, community, flag = 1;
        ip = $('#ip').val();


        var ipformat = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
        ipformat = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
        if (ip.match(ipformat)) {
            $('#ip').css("border", "green solid 2px");
            $('#ipSpan').hide();
//                $('#formid').reset();

        } else {
            flag = 0;
            $('#ipSpan').show();
            $('#ip').css("border", "red solid 1px");

        }
        community = $('#community').val();

        if (community == '') {
            flag = 0;
            $('#communitySpan').show();
            $('#community').css("border", "#ff5783 solid 1px");
        }
        else {
//                $('#formid').reset();
        }
        console.log(ip + community);

        if (flag == 1) {

            $.ajax({
                type: "Get",
                url: "loginAjax",
                data: "ip=" + ip + "&community=" + community,
                success: function (response) {
                    console.log(" addNewAjax" + response);
                    if (response != "duplicateDevice") {
                        response = jQuery.parseJSON(response);
                        createAccordion(response);
                        $('#correct').modal("show");
                        setTimeout(function () {
                            $('#correct').modal('hide')
                        }, 1500);
                    }
                    else {
                        $('#duplicateDevice').modal("show");
                        setTimeout(function () {
                            $('#duplicateDevice').modal('hide')
                        }, 2500);

                    }
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }
    }

</script>

<script type="text/javascript">

    function createAccordion(deviceJson) {
        console.log("length = " + Object.keys(deviceJson).length);
        $("#tbody").empty();
        for (var id = 1; id <= Object.keys(deviceJson).length / 2; id++) {
            if ((id + 3) % 4 == 0)
                var colour = "info";
            if ((id + 2) % 2 == 0)
                colour = "success";
            if ((id + 1) % 4 == 0)
                colour = "warning";
            if (id % 4 == 0)
                colour = "danger";

            var jsonip = deviceJson["Ip" + id];
            var jsoncommunity = deviceJson["Community" + id];
            var jsonmac = deviceJson["mac" + id];

            var tr = "<tr>" +
                    "<td>" + jsonip + "</td>" +
                    "<td>" + jsoncommunity + "</td>" +
                    "<td>" + jsonmac + "</td>" +
                    "<td><button class='btn btn-success' type='button' value='Load' onclick='showLiveTable(\"" + jsonip + "\",\"" + jsoncommunity + "\")' " +
                    "\>Show live</button></td>" +
                    "<td><button class='btn btn-danger' type='button' value='Load' onclick='removeRow(\"" + jsonip + "\")' " +
                    "\>Delete</button></td> " +
                    "</tr>";
            $("#tbody").append(tr);
        }
    }
        function loadjs(){
        $.getScript("./resources/vendor/datatables/media/js/jquery.dataTables.min.js", function()
        {});
        $.getScript("./resources/vendor/datatables-colvis/js/dataTables.colVis.js", function()
        {});
        $.getScript("./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js", function()
        {});
        $.getScript("./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js", function()
        {});
        $.getScript("./resources/vendor/datatables/demo-datatable.js", function()
        {});
        }

</script>



<div class="modal fade" id="duplicateDevice" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error</h4>
            </div>
            <div class="modal-body">
                <p>This device already exists </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="correct" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">success</h4>
            </div>
            <div class="modal-body">
                <p> Thumbs up :) </p>
            </div>
        </div>

    </div>
</div>
<%--model ends--%>
<%--<script src="./resources/vendor/datatables/media/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="./resources/vendor/datatables-colvis/js/dataTables.colVis.js"></script>--%>
<%--<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js"></script>--%>
<%--<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js"></script>--%>
<%--<script src="./resources/vendor/datatables/demo-datatable.js"></script>--%>
</body>
<!-- DATATABLES-->

</html>
