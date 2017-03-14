<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/22/2016
  Time: 9:52 AM
--%>
<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap-rtl.css"/>--%>
<%--<script type="text/javascript" src="./resources/js/jquery-1.12.0.min.js"></script>--%>
<!-- Main section-->
<style>
    .content-wrapper > h3,/* .content-wrapper > .content-heading*/ {
        margin-bottom: 4px;
        padding: 4px;
    }
    .wrapper > section{
        top: -20px;
    }
</style>
<section>


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
        for (var id = 1; id <= Object.keys(deviceJson).length / 3; id++) {

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
//                    $("#accordion1").append(content1);
            $("#tbody").append(tr);
        }
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
                <h4 class="modal-title">Device registered successfully.</h4>
            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="CSVerror" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error: This is not a CSV file.</h4>
            </div>
        </div>

    </div>
</div>
<%--model ends--%>
</body>

<script>
    $("#filename").change(function (e) {

        var ext = $("input#filename").val().split(".").pop().toLowerCase();

        if ($.inArray(ext, ["csv"]) == -1) {
//            alert('Error:Upload a CSV file.');
            $('#CSVerror').modal('show')
            setTimeout(function () {
                $('#CSVerror').modal('hide');
            }, 3000);
            return false;
        }
        if (e.target.files != undefined) {

            var reader = new FileReader();
            reader.onload = function (e) {
                var csvval = e.target.result;
                console.log(csvval);
                $('#spinnerCSV').addClass("fa fa-spinner fa-spin fa-fw fa-lg");
                $('#spinnerCSV').show();
                $.ajax({
                    type: "Get",
                    url: "csvIpCommunity",
                    data: "ipCommunityBulkString=" + csvval,
                    success: function (response) {
                        if (response != "duplicateDevice") {
                            response = jQuery.parseJSON(response);
                            createAccordion(response);
                            $('#correct').modal("show");
                            setTimeout(function () {
                                $('#correct').modal('hide')
                                $('#spinnerCSV').hide();

                            }, 1500);
                        }
                        else {
                            $('#duplicateDevice').modal("show");
                            setTimeout(function () {
                                $('#duplicateDevice').modal('hide')
                                $('#spinnerCSV').hide();

                            }, 1500);

                        }
                    },
                    error: function (e) {
                        $("#spinner").hide();
                        alert('Error: ' + e);
                    }
                });

            };
            reader.readAsText(e.target.files.item(0));

        }

        return false;

    });</script>
<!-- FILESTYLE-->
<script src="./resources/vendor/bootstrap-filestyle/src/bootstrap-filestyle.js"></script>
<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
<!-- CLASSY LOADER-->
<script src="./resources/vendor/jquery-classyloader/js/jquery.classyloader.min.js"></script>

</html>
