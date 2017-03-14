/**
 * Created by Rushil Mahaan on 2/15/2016.
 */
    // funtion for process map
/*
1. Processes
2. SW_installed
3. Storage
4. Device
5. Interface
6. System
*/


var processTableCount = 0;
function createDiv1(jsonData) {

    $('#div6').hide("fast");
    $('#div2').hide("fast");
    $('#div3').hide("fast");
    $('#div4').hide("fast");
    $('#div5').hide("fast");
    $('#div1').show("fast");
    $("#div1").css("padding-top", "2%");


    if (processTableCount == 0) {

        var content = "<TR><TD>Name</TD><TD>Type</TD><TD>Run Status</TD><TD>Cpu Performance(sec)</TD><TD>Memory</TD>" +
                        "</TR>";
        $("#thead").append(content);
        processTableCount++;
    }
    $('#tBody').empty();
    $.each(jsonData, function (index, jsonObject) {
        var tr = document.createElement("TR");

        var td1 = document.createElement("TD");
        var name = document.createTextNode(jsonObject["name"]);
        td1.appendChild(name);
        tr.appendChild(td1);

        var td2 = document.createElement("TD");
        var runType = document.createTextNode(jsonObject["runType"]);
        td2.appendChild(runType);
        tr.appendChild(td2);


        var td3 = document.createElement("TD");
        var runStatus = document.createTextNode(jsonObject["runStatus"]);
        td3.appendChild(runStatus);
        tr.appendChild(td3);

        var td4 = document.createElement("TD");
        var cpuPerformance = document.createTextNode((jsonObject["cpuPerformance"]/100).toFixed(2));
        td4.appendChild(cpuPerformance);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        jsonObject["memory"] = jsonObject["memory"]/1024;
        var memory = document.createTextNode(jsonObject["memory"].toFixed(1)+" MB");
        td5.appendChild(memory);
        tr.appendChild(td5);

        document.getElementById("tBody").appendChild(tr);

        // console.log("name : " + jsonObject["name"]);
    });
/*
 // to convert a table into data table
 var table = $('#example').DataTable();
 var tt = new $.fn.dataTable.TableTools(table);
 $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
 $('div.DTTT_container').remove();


 */

    $('#datatableProcesses').dataTable({
         //destroy: true,
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


// funtion for swInstalledmap

var swTableCount = 0;
function createDiv2(jsonData) {

    $('#div1').hide("fast");
    $('#div6').hide("fast");
    $('#div3').hide("fast");
    $('#div4').hide("fast");
    $('#div5').hide("fast");
    $('#div2').show("fast");
    $("#div2").css("padding-top", "2%");

    if (swTableCount == 0) {
        var content = "<TR><TD>SW Installed</TD><TD>Install Date</TD>" +
            "</TR>";
        $("#thead2").append(content);
        swTableCount++;
    }

    $('#tBody2').empty();

        $.each(jsonData, function (index, jsonObject) {
            var tr = document.createElement("TR");
            var td1 = document.createElement("TD");
            var swInstalledName = document.createTextNode(jsonObject["swInstalledName"]);
            td1.appendChild(swInstalledName);
            tr.appendChild(td1);

            var td2 = document.createElement("TD");
            var swInstalledDate = document.createTextNode(jsonObject["swInstalledDate"]);
            td2.appendChild(swInstalledDate);
            tr.appendChild(td2);
            document.getElementById("tBody2").appendChild(tr);

        });


    $('#datatableSW_installed').dataTable({
        //destroy: true,
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
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

  /*  $('#datatableSW_installed').dataTable({
        destroy: true
    });*/
}

// funtion for storageMap
var storageCount = 0;
function createDiv3(jsonData) {

    $('#div1').hide("fast");
    $('#div2').hide("fast");
    $('#div6').hide("fast");
    $('#div4').hide("fast");
    $('#div5').hide("fast");
    $('#div3').show("fast");
    $("#div3").css("padding-top", "2%");

    if (storageCount == 0) {

        var content = "<TR><TD>Description</TD><TD>Drive Details</TD><TD>Type</TD>" +
            "</TR>";
        $("#thead3").append(content);
        storageCount++;
    }
    $("#tBody3").empty();

    $.each(jsonData, function (index, jsonObject) {
        var tr = document.createElement("TR");

        var td2 = document.createElement("TD");
        var td = jsonObject["description"];
        //if(td=='Physical Memory') {td = 'RAM';}
        var description = document.createTextNode(td);
        td2.appendChild(description);
        tr.appendChild(td2);

        var td2 = document.createElement("TD");
        var td = jsonObject["description"];
        var totalSpace=jsonObject["size"]/(1024*1024*1024);
        var usedSpace=jsonObject["used"]/(1024*1024*1024);
        var free_space = totalSpace-usedSpace;
        totalSpace = totalSpace.toFixed(2);
        free_space = free_space.toFixed(2);
        var usedPercent=usedSpace*100/totalSpace;
      //  if(usedPercent>60&&usedPercent<90){$(".progress-bar-info").css("background-color", "yellow");}
      //   if(usedPercent>89){
      //       $('.progress-bar-info').css('background-color', 'red');
      //   }
        var content="<div class='progress'><div role='progressbar' aria-valuenow='20' aria-valuemin='0' " +
            "aria-valuemax='"+totalSpace+"' style='width:"+usedPercent+"%' class='progress-bar progress-bar-info'> " +
                "<span class='sr-only'></span></div> </div><div> "+free_space+"GB free of "+totalSpace+" GB</div>";
        $(td2).append(content);
        $(tr).append(td2);

        var td2 = document.createElement("TD");
        var hrtype=jsonObject["type"];
        if(hrtype==4){hrtype="Local Disk"}
        else if(hrtype==7){hrtype="DVD RW Drive"}
        else if(hrtype==2){hrtype="RAM"}
        else if(hrtype==3){hrtype="Virtual"}
        else {hrtype="External Drive"}
        var type = document.createTextNode(hrtype);
        td2.appendChild(type);
        tr.appendChild(td2);


        document.getElementById("tBody3").appendChild(tr);

    });
    $('#datatableStorage').dataTable({
        //destroy: true,
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
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

    //$('#datatableStorage').dataTable({destroy: true});
}


// funtion for device Map
var deviceCount = 0;
function createDiv4(jsonData) {

    $('#div1').hide("fast");
    $('#div2').hide("fast");
    $('#div3').hide("fast");
    $('#div6').hide("fast");
    $('#div5').hide("fast");
    $('#div4').show("fast");
    $("#div4").css("padding-top", "2%");

    if(deviceCount == 0)
    {
        var content = "<TR><TD>Device Description</TD><TD>Device Status</TD><TD>Device Errors</TD></TR>";
        $("#thead4").append(content);
        deviceCount++;
    }

    $.each(jsonData, function (index, jsonObject) {
        var tr = document.createElement("TR");

        td = document.createElement("TD");
        var hrDeviceDescr = document.createTextNode(jsonObject["hrDeviceDescr"]);
        td.appendChild(hrDeviceDescr);
        tr.appendChild(td);

        td = document.createElement("TD");
        if(jsonObject["hrDeviceStatus"]==1) {jsonObject["hrDeviceStatus"]="Unknown"}
        else if(jsonObject["hrDeviceStatus"]==2) {jsonObject["hrDeviceStatus"]="Running"}
        var hrDeviceStatus = document.createTextNode(jsonObject["hrDeviceStatus"]);
        td.appendChild(hrDeviceStatus);
        tr.appendChild(td);

        td = document.createElement("TD");
        var error = jsonObject["hrDeviceErrors"];
        if(error==0){error='None'}
        var hrDeviceErrors = document.createTextNode(error);
        td.appendChild(hrDeviceErrors);
        tr.appendChild(td);


        document.getElementById("tBody4").appendChild(tr);

    });

    $('#datatableDevice').dataTable({
//        destroy: true,
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
            {'bVisible':true}
        ],
        sDom:      'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'
        }
    });

/*
    $('#datatableDevice').dataTable({destroy: true});

*/

}


// funtion for interface Map
var interfaceCount = 0;
function createDiv5(jsonData) {

    $('#div1').hide("fast");
    $('#div2').hide("fast");
    $('#div3').hide("fast");
    $('#div4').hide("fast");
    $('#div6').hide("fast");
    $('#div5').show("fast");
    $("#div5").css("padding-top", "2%");

    if (interfaceCount == 0) {
        var content = "<TR><TD>Name</TD><TD>MAC</TD><TD>Adminstrator Status</TD><TD>Operational Status</TD><TD>Speed (MBps)</TD><TD>In Octets</TD><TD>Out Octets</TD></TR>";
        $("#thead5").append(content);
        interfaceCount++;
    }
    $('#tBody5').empty();

    $.each(jsonData, function (index, jsonObject) {

        var tr = document.createElement("TR");

        var td1 = document.createElement("TD");
        var name = document.createTextNode(jsonObject["ifDescr"]);
        td1.appendChild(name);
        tr.appendChild(td1);

        var td2 = document.createElement("TD");
        var mac = document.createTextNode(jsonObject["ifPhysAddress"]);
        td2.appendChild(mac);
        tr.appendChild(td2);

        var td3 = document.createElement("TD");
        var adminStatus = document.createTextNode(jsonObject["ifAdminStatus"]);
        td3.appendChild(adminStatus);
        tr.appendChild(td3);

        var td4 = document.createElement("TD");
        var operStatus = document.createTextNode(jsonObject["ifOperStatus"]);
        td4.appendChild(operStatus);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        var speed = document.createTextNode((jsonObject["ifSpeed"]/(8192*1024)).toFixed(2));
        td5.appendChild(speed);
        tr.appendChild(td5);

        var td6 = document.createElement("TD");
        var inOctets = document.createTextNode(jsonObject["ifInOctets"]);
        td6.appendChild(inOctets);
        tr.appendChild(td6);

        var td7 = document.createElement("TD");
        var outOctets = document.createTextNode(jsonObject["ifOutOctets"]);
        td7.appendChild(outOctets);
        tr.appendChild(td7);

        document.getElementById("tBody5").appendChild(tr);
    });
    $('#datatableInterface').dataTable({

        'paging': true,  // Table pagination
        'ordering': true,  // Column ordering
        'info': true,  // Bottom left status text
        // Text translation options
        // Note the required keywords between underscores (e.g _MENU_)
        oLanguage: {
            sSearch: 'Search all columns:',
            sLengthMenu: '_MENU_ records per page',
            info: 'Showing page _PAGE_ of _PAGES_',
            zeroRecords: 'Nothing found - sorry',
            infoEmpty: 'No records available',
            infoFiltered: '(filtered from _MAX_ total records)'
        },
        // set columns options
        'aoColumns': [
            {'bVisible': true},
            {'bVisible': true},
            {'bVisible': true},
            {'bVisible': true},
            {'bVisible': true},
            {'bVisible': true},
            {'bVisible': true}
        ],
        sDom: 'C<"clear">lfrtip',
        colVis: {
            order: 'alfa',
            'buttonText': 'Show/Hide Columns'


        },

    });
/*
    $('#datatableInterface').dataTable({
        destroy: true

    });*/
}
// funtion for system map

var systemCount = 0;
function createDiv6(jsonData) {
    //console.log(jsonData);
    $('#tBody6').empty();
    $('#div1').hide("fast");
    $('#div2').hide("fast");
    $('#div3').hide("fast");
    $('#div4').hide("fast");
    $('#div5').hide("fast");
    $('#div6').show("fast");
    $("#div6").css("padding-top", "2%");


            var printer = jsonData["hrPrinterStatus"];
            var pr_status;
            if (printer == 3) {
                pr_status = "Idle";
            }
            else if (printer == 4) {
                pr_status = "Printing";
            }
            else {
                pr_status = "Unknown";
            }
            var content = "<TR><TD>Name</TD><TD>"+jsonData['sysName']+"</TD></TR>" +
                          "<TR><TD>Description</TD><TD>"+jsonData['sysDescr']+"</TD></TR>" +
                          "<TR><TD>IP</TD><TD>"+jsonData['ipAdEntAddr']+"</TD></TR>" +
                          "<TR><TD>Location</TD><TD>"+jsonData['sysLocation']+"</TD></TR>" +
                          "<TR><TD>Contact</TD><TD>"+jsonData['sysContact']+"</TD></TR>" +
                          "<TR><TD>Up Time</TD><TD>"+jsonData['hrSystemUptime']+"</TD></TR>" +
                          "<TR><TD>Running Processes</TD><TD>"+jsonData['hrSystemProcesses']+"</TD></TR>" +
                          "<TR><TD>Printer Status</TD><TD>"+pr_status+"</TD></TR>" +
                          "<TR><TD>Last SW Chanage</TD><TD>"+jsonData['hrSWInstalledLastChange']+"</TD></TR>";

            $("#tBody6").append(content);

//    $('#datatableSystem').dataTable({
//        //destroy: true,
//        'paging':   false,  // Table pagination
//        'ordering': false,  // Column ordering
//        'info':     true,  // Bottom left status text
//        'searching':   false,
//         'buttons': [
//            {
//                extend: 'pdfHtml5',
//                orientation: 'landscape',
//                pageSize: 'LEGAL'
//            }
//        ],
//        // Text translation options
//        // Note the required keywords between underscores (e.g _MENU_)
//        oLanguage: {
//            sSearch:      'Search all columns:',
//            sLengthMenu:  '_MENU_ records per page',
//            info:         'Showing page _PAGE_ of _PAGES_',
//            zeroRecords:  'Nothing found - sorry',
//            infoEmpty:    'No records available',
//            infoFiltered: '(filtered from _MAX_ total records)'
//        },
//        sDom:      'C<"clear">lfrtip',
//        colVis: {
//            order: 'alfa',
//            'buttonText': 'Show/Hide Columns',
//        },
////        destroy: true
//
//    });
/*

    $('#datatableSystem').dataTable({
        destroy: true
    });
*/

    }
