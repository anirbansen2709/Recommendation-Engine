<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<!-- =============== PAGE VENDOR STYLES ===============-->
<!-- TAGS INPUT-->
<link rel="stylesheet" href="./resources/vendor/bootstrap-tagsinput/dist/bootstrap-tagsinput.css">
<!-- SLIDER CTRL-->
<link rel="stylesheet" href="./resources/vendor/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css">
<!-- CHOSEN-->
<link rel="stylesheet" href="./resources/vendor/chosen_v1.2.0/chosen.min.css">
<%--
<!-- DATETIMEPICKER-->
<link rel="stylesheet" href="./resources/vendor/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css">
--%>
<link rel="stylesheet" href="./resources/buttonAddSub/css/stylecopy.css">
<script src="./resources/buttonAddSub/js/jquery.min.js"></script>
<script src="./resources/buttonAddSub/js/incrementing.js"></script>


<style>
    .slider.slider-horizontal {
        width: 100%;
    }

    .avoid-clicks {
        pointer-events: none;
        opacity: 0.1;
        background: initial;
    }

</style>
<!-- Main section-->

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
<!-- SLIDER CTRL-->
<script src="./resources/vendor/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js"></script>
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


<!-- FILESTYLE-->
<script src="./resources/vendor/bootstrap-filestyle/src/bootstrap-filestyle.js"></script>
<!-- TAGS INPUT-->
<script src="./resources/vendor/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<!-- CHOSEN-->
<script src="./resources/vendor/chosen_v1.2.0/chosen.jquery.min.js"></script>
<!-- SLIDER CTRL-->
<script src="./resources/vendor/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js"></script>
<!-- INPUT MASK-->
<script src="./resources/vendor/jquery.inputmask/dist/jquery.inputmask.bundle.min.js"></script>
<!-- WYSIWYG-->
<script src="./resources/vendor/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>
<script src="./resources/vendor/bootstrap-wysiwyg/external/jquery.hotkeys.js"></script>
<!-- MOMENT JS-->
<script src="./resources/vendor/moment/min/moment-with-locales.min.js"></script>
<!-- DATETIMEPICKER-->
<script type="text/javascript"
        src="./resources/vendor/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
<!-- Demo-->
<script src="./resources/js/demo/demo-forms.js"></script>


</body>
<script>
    $("#pendriveStatus").click(function (e) {
    });

    $("#ipStatus").click(function (e) {
//        console.log("IP:" + $('#ipStatus').is(':checked'));
    });

    $("#downloadStatus").click(function (e) {
        var status = $('#downloadStatus').is(':checked');
        if (status == true) {
            $("#download").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#download").addClass('avoid-clicks');
        }
    });

    $("#uploadStatus").click(function (e) {
        var status = $('#uploadStatus').is(':checked');
        if (status == true) {
            $("#upload").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#upload").addClass('avoid-clicks');
        }
    });

    $("#freeDriveSpaceStatus").click(function (e) {
        var status = $('#freeDriveSpaceStatus').is(':checked');
        if (status == true) {
            $("#fds").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#fds").addClass('avoid-clicks');
        }
    });

    $("#processStatus").click(function (e) {
        var status = $('#processStatus').is(':checked');
        if (status == true) {
            $("#process").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#process").addClass('avoid-clicks');
        }
    });

    $("#upTimeStatus").click(function (e) {
        var status = $('#upTimeStatus').is(':checked');
        if (status == true) {
            $("#ut").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#ut").addClass('avoid-clicks');
        }
    });

    $("#ramStatus").click(function (e) {
        var status = $('#ramStatus').is(':checked');
        if (status == true) {
            $("#ram").removeClass('avoid-clicks');
        }
        else if (status == false) {
            $("#ram").addClass('avoid-clicks');
        }
    });
    $("#save").click(function (e) {
        save();
    });


    $(document).ready(function () {
        loadSettings();
    });
    function loadSettings() {
        console.log("load settings()");
        $.ajax({
            type: "Get",
            url: "extractNotificationSettings",
            success: function (NotificationSettings) {
                NotificationSettings = jQuery.parseJSON(NotificationSettings);
                console.log(NotificationSettings);
                var pendriveStatus = status(NotificationSettings["pendrive_detection_status"]);
                var ipStatus = status(NotificationSettings["ip_change_detection_status"]);
                var ramStatus = status(NotificationSettings["ram_detection_status"]);
                var processStatus = status(NotificationSettings["process_detection_status"]);
                var uploadStatus = status(NotificationSettings["upload_detection_status"]);
                var downloadStatus = status(NotificationSettings["download_detection_status"]);
                var freeDriveSpaceStatus = status(NotificationSettings["free_space_detection_status"]);
                var upTimeStatus = status(NotificationSettings["uptime_detection_status"]);


                $('#pendriveStatus').prop('checked', pendriveStatus); // Checks it or ucheck it
                $('#ipStatus').prop('checked', ipStatus); // Checks it or ucheck it
                $('#ramStatus').prop('checked', ramStatus); // Checks it or ucheck it
                if (ramStatus == true) {
                    $("#ram").removeClass('avoid-clicks');
                }
                else if (ramStatus == false) {
                    $("#ram").addClass('avoid-clicks');
                }

                $('#processStatus').prop('checked', processStatus); // Checks it or ucheck it
                if (processStatus == true) {
                    $("#process").removeClass('avoid-clicks');
                }
                else if (processStatus == false) {
                    $("#process").addClass('avoid-clicks');
                }

                $('#downloadStatus').prop('checked', downloadStatus); // Checks it or ucheck it
                if (downloadStatus == true) {
                    $("#download").removeClass('avoid-clicks');
                }
                else if (downloadStatus == false) {
                    $("#download").addClass('avoid-clicks');
                }

                $('#uploadStatus').prop('checked', uploadStatus); // Checks it or ucheck it
                if (uploadStatus == true) {
                    $("#upload").removeClass('avoid-clicks');
                }
                else if (uploadStatus == false) {
                    $("#upload").addClass('avoid-clicks');
                }

                $('#freeDriveSpaceStatus').prop('checked', freeDriveSpaceStatus); // Checks it or ucheck it
                if (freeDriveSpaceStatus == true) {
                    $("#fds").removeClass('avoid-clicks');
                }
                else if (freeDriveSpaceStatus == false) {
                    $("#fds").addClass('avoid-clicks');
                }

                $('#upTimeStatus').prop('checked', upTimeStatus); // Checks it or ucheck it
                if (upTimeStatus == true) {
                    $("#ut").removeClass('avoid-clicks');
                }
                else if (upTimeStatus == false) {
                    $("#ut").addClass('avoid-clicks');
                }

                var uptimeToken = timeConverter(NotificationSettings["uptime_limit"]);
                var dataUpTime = uptimeToken.value;
                var bytesUpTime = uptimeToken.unit;

                var uploadToken = dataUnitConverter(NotificationSettings["upload_limit"]);
                var dataUpload = uploadToken.value;
                var bytesUpload = uploadToken.unit;

                var downloadToken = dataUnitConverter(NotificationSettings["download_limit"]);
                var dataDownload = downloadToken.value;
                var bytesDownload = downloadToken.unit;

                var freeSpaceToken = dataUnitConverter(NotificationSettings["free_space_limit"]);
                var dataFreeDriveSpace = freeSpaceToken.value;
                var bytesFreeDriveSpace = freeSpaceToken.unit;

                $("#ramInput").prop('value', NotificationSettings["ram_limit"]);

                $("#processInput").prop('value', NotificationSettings["process_name"]);

                $("#dataDownload").prop('value', dataDownload);
                $("#bytesDownload").prop('value', bytesDownload);

                $("#dataUpload").prop('value', dataUpload);
                $("#bytesUpload").prop('value', bytesUpload);

                $("#dataFreeDriveSpace").prop('value', dataFreeDriveSpace);
                $("#bytesFreeDriveSpace").prop('value', bytesFreeDriveSpace);

                $("#dataUpTime").prop('value', dataUpTime);
                $("#bytesUpTime").prop('value', bytesUpTime);


            },
            error: function (e) {
                alert('Error:' + e);
            }
        });
    }

    function save() {
        //pendrive
        var pendriveStatus = $('#pendriveStatus').is(':checked');

        //IP change
        var ipStatus = $('#ipStatus').is(':checked');

        //RAM
        var ramStatus = $('#ramStatus').is(':checked');
        var ramInput = $("#ramInput").val();
        //process
        var processStatus = $('#processStatus').is(':checked');
        var processInput = $("#processInput").val();

        //data usage - download
        var downloadStatus = $('#downloadStatus').is(':checked');
        var dataDownload = $("#dataDownload").val();
        var bytesDownload = $("#bytesDownload").val();

        //data usage - upload
        var uploadStatus = $('#uploadStatus').is(':checked');
        var dataUpload = $("#dataUpload").val();
        var bytesUpload = $("#bytesUpload").val();

        //free sppace
        var freeDriveSpaceStatus = $('#freeDriveSpaceStatus').is(':checked');
        var dataFreeDriveSpace = $("#dataFreeDriveSpace").val();
        var bytesFreeDriveSpace = $("#bytesFreeDriveSpace").val();

        //uptime
        var upTimeStatus = $('#upTimeStatus').is(':checked');
        var dataUpTime = $("#dataUpTime").val();
        var bytesUpTime = $("#bytesUpTime").val();
        console.log("\npendriveStatus=" + pendriveStatus + "\n\nipStatus=" + ipStatus + "\n\nramStatus=" + ramStatus + "\nramInput=" + ramInput
                + "\n\nprocessStatus=" + processStatus + "\nprocessInput=" + processInput
                + "\n\ndStatus=" + downloadStatus + "\ndataDw=" + dataDownload + "\nbytesD=" + bytesDownload
                + "\n\nuploadStatus=" + uploadStatus + "\ndataDataUsage=" + dataUpload + "\nbytesDataUsage=" + bytesUpload
                + "\n\nfreeDriveSpaceStatus=" + freeDriveSpaceStatus + "\ndataFreeDriveSpace=" + dataFreeDriveSpace + "\nbytesFreeDriveSpace=" + bytesFreeDriveSpace
                + "\n\nupTimeStatus=" + upTimeStatus + "\ndataUpTime=" + dataUpTime + "\nupTimeStatus=" + bytesUpTime);
        $.ajax({
            url: "saveNotificationSettings",
            data: "pendriveStatus=" + pendriveStatus + "&ipStatus=" + ipStatus + "&ramStatus=" + ramStatus + "&ramInput=" + ramInput
            + "&processStatus=" + processStatus + "&processInput=" + processInput
            + "&downloadStatus=" + downloadStatus + "&dataDownload=" + dataDownload + "&bytesDownload=" + bytesDownload
            + "&uploadStatus=" + uploadStatus + "&dataUpload=" + dataUpload + "&bytesUpload=" + bytesUpload
            + "&freeDriveSpaceStatus=" + freeDriveSpaceStatus + "&dataFreeDriveSpace=" + dataFreeDriveSpace + "&bytesFreeDriveSpace=" + bytesFreeDriveSpace
            + "&upTimeStatus=" + upTimeStatus + "&dataUpTime=" + dataUpTime + "&bytesUpTime=" + bytesUpTime,
            success: function () {
                loadSettings();
            },
            error: function (e) {
                alert('Error:' + e);
            }
        });
    }

    function status(status) {
        if (status == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    function timeConverter(time) {
        var t = {value: "", unit: ""};
        if ((time % (3600 * 24 * 30)) == 0) {
            t.value = time / (3600 * 24 * 30);
            t.unit = "months";
            return t
        }
        else if ((time % (3600 * 24)) == 0) {
            t.value = time / (3600 * 24);
            t.unit = "days";
            return t
        }
        else if ((time % 3600) == 0) {
            t.value = time / 3600;
            t.unit = "hours";
            return t
        }
        else if ((time % 60) == 0) {
            t.value = time / 60;
            t.unit = "mins";
            return t
        }


    }

    function dataUnitConverter(data) {
        var dataToken = {value: "", unit: ""};

        if (data % (1024 * 1024 * 1024) == 0) {
            dataToken.value = data / (1024 * 1024 * 1024);
            dataToken.unit = "GB";
            return dataToken;
        }
        else if (data % (1024 * 1024) == 0) {
            dataToken.value = data / (1024 * 1024);
            dataToken.unit = "MB";
            return dataToken;

        }
        else if (data % (1024) == 0) {
            dataToken.value = data / 1024;
            dataToken.unit = "KB";
            return dataToken;

        }
        else {
            return -1;
        }
    }

</script>
</html>