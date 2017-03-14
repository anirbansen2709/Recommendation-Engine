/**
 * Created by Rushil Mahaan on 04-03-2016.
 */
//script for data tables in various test

//<%--script for creating datat table--%>

// onclick events by tabs
$("#pendrive").click(function (e) {
    //$("#style11").css("padding-top", "3%");
    $("#lastMonth").empty();
    test("pendrive");
});
$("#printer").click(function (e) {
    $("#lastMonth").empty();
    $("#style12").css("padding-top", "3%");
    test("printer");
});

$("#ram").click(function (e) {
    $("#limit2GB").empty();
    test("ram");
});

$("#driveSpace").click(function (e) {
    $("#style22").css("padding-top", "3%");
    test("driveSpace");
});

$("#upload").click(function (e) {
    $("#limit100MB").empty();
    $("#style32").css("padding-top", "3%");
    test("upload");
});

$("#download").click(function (e) {
    $("#limit100MB").empty();
    test("download");
});

$("#systemUpTime").click(function (e) {
    $("#uptime3hrs1day").empty();
    test("systemUpTime");
});

$("#process").click(function (e) {
    $("#uptime3hrs1day").empty();
    $("#style42").css("padding-top", "3%");
    test("process");
});

$("#softwareUpdate").click(function (e) {
    $("#uptime3hrs1day").empty();
    $("#style43").css("padding-top", "3%");
    test("softwareUpdate");

});
$("#duplicateIp").click(function (e) {
    $("#style44").css("padding-top", "3%");
    test("duplicateIp");
    $("#uptime3hrs1day").empty();

});


function createPendriveBody(deviceDetails) {
    $("#style11").css("padding-top", "3%");
    $("#thead1").empty();
    $("#tBody1").empty();
    $("#thead1").append("<tr><td>Time of Detection</td><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Description</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var desc = value[4];
        var timeOfDetection = value[5];

        for (var countvalue = 6; countvalue <= value.length; countvalue++) {
            $("#tBody1").append("<tr><td>" + timeOfDetection + "</td><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+desc+"</td></tr>");

            desc= value[countvalue];

            countvalue++;
            timeOfDetection = value[countvalue];
        }
    });
/*
        var table = $('#example1').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();

*/
    $('#examplePendrive').dataTable({
      //  destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });


}
/*
function createPendriveBody(deviceDetails) {
    $("#thead1").empty();
    $("#tBody1").empty();
    $("#thead1").append("<tr><td>Time of Detection</td><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Description</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var desc = value[4];
        var timeOfDetection = "";
        for (var countvalue = 5; countvalue < value.length; countvalue++) {
            timeOfDetection = value[countvalue];
            $("#tBody1").append("<tr><td>" + timeOfDetection + "</td><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+desc+"</td></tr>");
        }
    });
        var table = $('#example1').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();


}
*/
function createPrinterBody(deviceDetails) {
    $("#thead2").empty();
    $("#tBody2").empty();
    $("#thead2").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Time of Detection</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var timeOfDetection = "";
        for (var countvalue = 5; countvalue < value.length; countvalue++) {
            timeOfDetection = value[countvalue];
            $("#tBody2").append("<tr><td>" + timeOfDetection + "</td><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td></tr>");
        }
    });/*
        var table = $('#example2').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();

*/

    $('#examplePrinter').dataTable({
    //    destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });


}
function createRamBody(deviceDetails) {
    $("#style21").css("padding-top", "3%");
    $("#theadRam").empty();
    $("#tbodyRam").empty();
    $("#theadRam").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Size</td></tr>");

    console.log("deviceDetails = "+deviceDetails);
    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var size = value[4];
        size = size/(1024*1024*1024);
        size = size.toFixed(2);
        $("#tbodyRam").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+size+" GB</td></tr>");
    });
/*
        var table = $('#exampleRam').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();

*/
    $('#exampleRam').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createDriveSpaceBody(deviceDetails) {
    $("#theadDriveSpace").empty();
    $("#tbodyDriveSpace").empty();
    $("#theadDriveSpace").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Description</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var desc = "Drives : ";
        for (var countvalue = 4; countvalue < value.length; countvalue++) {
            desc = desc +" "+ value[countvalue]+" ,  ";
        }

            $("#tbodyDriveSpace").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+desc+"</td></tr>");
    });
   /*     var table = $('#exampleDriveSpace').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();

*/
    $('#exampleDriveSpace').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createUploadBody(deviceDetails) {
    $("#theadUpload").empty();
    $("#tbodyUpload").empty();
    $("#theadUpload").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Upload</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var upload = value[4];
        upload = upload/(1024);
        upload = upload.toFixed(2);

        $("#tbodyUpload").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+upload+"KB</td></tr>");

    });/*
        var table = $('#exampleUpload').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/
    $('#exampleUpload').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });


}

function createDownloadBody(deviceDetails) {
    $("#style31").css("padding-top", "3%");
    $("#theadDownload").empty();
    $("#tbodyDownload").empty();
    $("#theadDownload").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Download</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var download = value[4];
        download = download/(1024);
        download = download.toFixed(2);

        $("#tbodyDownload").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>"+download+"KB</td></tr>");

    });/*
        var table = $('#exampleDownload').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/
    $('#exampleDownload').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createSystemUpTimeBody(deviceDetails) {
    $("#style41").css("padding-top", "3%");
    $("#theadSystemUpTime").empty();
    $("#tbodySystemUpTime").empty();
    $("#theadSystemUpTime").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        $("#tbodySystemUpTime").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td></tr>");

    });/*
        var table = $('#exampleSystemUpTime').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/

    $('#exampleSystemUpTime').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createProcessBody(deviceDetails) {
    $("#theadProcess").empty();
    $("#tbodyProcess").empty();
    $("#theadProcess").append("<tr><td>Time Of Detection</td><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var timeOfDetection = "";
        for (var countvalue = 4; countvalue < value.length; countvalue++) {
            timeOfDetection = value[countvalue];
            $("#tbodyProcess").append("<tr><td>" + timeOfDetection + "</td><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td></tr>");
        }
    });
      /*  var table = $('#exampleProcess').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/
    $('#exampleProcess').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createSoftwareUpdateBody(deviceDetails) {
    $("#theadSoftwareUpdate").empty();
    $("#tbodySoftwareUpdate").empty();
    $("#theadSoftwareUpdate").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
            $("#tbodySoftwareUpdate").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td></tr>");

    });
/*

        var table = $('#exampleSoftwareUpdate').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/

    $('#exampleSoftwareUpdate').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}

function createDuplicateIpBody(deviceDetails) {
    $("#theadDuplicateIp").empty();
    $("#tbodyDuplicateIp").empty();
    $("#theadDuplicateIp").append("<tr><td>IP</td><td>MAC-Address</td><td>PC Name</td>" +
        "<td>Contact</td><td>Location</td><td>Duplicate IP</td></tr>");

    $.each(deviceDetails, function (index, value) {
        var mac = index;
        var ip = value[0];
        var name = value[1];
        var contact = value[2];
        var location = value[3];
        var duplicateIp = value[4];
            $("#tbodyDuplicateIp").append("<tr><td>" + ip + "</td><td>" + mac + "</td><td>" + name + "</td>" +
                "<td>" + contact + "</td><td>" + location + "</td><td>" + duplicateIp + "</td></tr>");

    });
/*

        var table = $('#exampleDuplicateIp').DataTable();
        var tt = new $.fn.dataTable.TableTools(table);
        $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
        $('div.DTTT_container').remove();
*/

    $('#exampleDuplicateIp').dataTable({
       // destroy: true,
        'paging':   true,  // Table pagination
        'ordering': true,  // Column ordering
        'info':     true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch:      'Search all columns:',
            sLengthMenu:  '_MENU_ records per page',
            info:         'Showing page _PAGE_ of _PAGES_',
            zeroRecords:  'Nothing found - sorry',
            infoEmpty:    'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true},
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

}
