/**
 * Created by lenovo on 2/15/2016.
 */
var processCount = 0;
var systemCount = 0;

function createProcessTable(jsonData) {


    if (processCount == 0) {
        var tr = document.createElement("TR");

        var td1 = document.createElement("TD");
        var name = document.createTextNode("Name");
        td1.appendChild(name);
        tr.appendChild(td1);

        var td2 = document.createElement("TD");
        var runType = document.createTextNode("Type");
        td2.appendChild(runType);
        tr.appendChild(td2);


        var td3 = document.createElement("TD");
        var runStatus = document.createTextNode("Status");
        td3.appendChild(runStatus);
        tr.appendChild(td3);

        var td4 = document.createElement("TD");
        var cpuPerformance = document.createTextNode("Cpu  centiseconds consumed");
        td4.appendChild(cpuPerformance);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        var memory = document.createTextNode("Memory");
        td5.appendChild(memory);
        tr.appendChild(td5);

        document.getElementById("head").appendChild(tr);
        processCount++;
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
        var cpuPerformance = document.createTextNode(jsonObject["cpuPerformance"]);
        td4.appendChild(cpuPerformance);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        var memory = document.createTextNode(jsonObject["memory"]);
        td5.appendChild(memory);
        tr.appendChild(td5);

        document.getElementById("tBody").appendChild(tr);
        //console.log("name : " + jsonObject["name"]);
        //console.log("type : " + jsonObject["runType"]);
        //console.log("status : " + jsonObject["runStatus"]);
        //console.log("cpu : "+ jsonObject["cpuPerformance"]);
        //console.log("memory : "+ jsonObject["memory"]);
    });

    var table = $('#example').DataTable();
    var tt = new $.fn.dataTable.TableTools(table);

    $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
    $('div.DTTT_container').remove();
}

function createInterfaceTable(jsonData) {

    if (systemCount == 0) {
        var tr = document.createElement("TR");

        var td1 = document.createElement("TD");
        var name = document.createTextNode("Name");
        td1.appendChild(name);
        tr.appendChild(td1);

        var td2 = document.createElement("TD");
        var mac = document.createTextNode("MAC");
        td2.appendChild(mac);
        tr.appendChild(td2);

        var td3 = document.createElement("TD");
        var adminStatus = document.createTextNode("Adminstrator Status");
        td3.appendChild(adminStatus);
        tr.appendChild(td3);

        var td4 = document.createElement("TD");
        var operStatus = document.createTextNode("Operational Status");
        td4.appendChild(operStatus);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        var speed = document.createTextNode("Speed");
        td5.appendChild(speed);
        tr.appendChild(td5);

        var td6 = document.createElement("TD");
        var inOctets = document.createTextNode("In Octets");
        td6.appendChild(inOctets);
        tr.appendChild(td6);

        var td7 = document.createElement("TD");
        var outOctets = document.createTextNode("Out Octets");
        td7.appendChild(outOctets);
        tr.appendChild(td7);

        document.getElementById("head").appendChild(tr);
        systemCount++;
    }

    $('#tBody').empty();

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
        var speed = document.createTextNode(jsonObject["ifSpeed"]);
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

        document.getElementById("tBody").appendChild(tr);
        //console.log("name : " + jsonObject["name"]);
        //console.log("type : " + jsonObject["runType"]);
        //console.log("status : " + jsonObject["runStatus"]);
        //console.log("cpu : "+ jsonObject["cpuPerformance"]);
        //console.log("memory : "+ jsonObject["memory"]);
    });

    var table = $('#example').DataTable();
    var tt = new $.fn.dataTable.TableTools(table);

    $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
    $('div.DTTT_container').remove();
}

var systemCount = 0;
function createSystemTable(jsonData) {

    if (systemCount == 0) {
        var tr = document.createElement("TR");

        var td1 = document.createElement("TD");
        var name = document.createTextNode("Name");
        td1.appendChild(name);
        tr.appendChild(td1);

        var td1 = document.createElement("TD");
        var desc = document.createTextNode("Description");
        td1.appendChild(desc);
        tr.appendChild(td1);

        var td1 = document.createElement("TD");
        var ip = document.createTextNode("IP");
        td1.appendChild(ip);
        tr.appendChild(td1);

        var td2 = document.createElement("TD");
        var location = document.createTextNode("Location");
        td2.appendChild(location);
        tr.appendChild(td2);

        var td3 = document.createElement("TD");
        var contact = document.createTextNode("Contact");
        td3.appendChild(contact);
        tr.appendChild(td3);

        var td4 = document.createElement("TD");
        var upTime = document.createTextNode("Up Time");
        td4.appendChild(upTime);
        tr.appendChild(td4);

        var td5 = document.createElement("TD");
        var process = document.createTextNode("Process Running");
        td5.appendChild(process);
        tr.appendChild(td5);

        var td6 = document.createElement("TD");
        var printerStatus = document.createTextNode("Printer Status");
        td6.appendChild(printerStatus);
        tr.appendChild(td6);

        var td7 = document.createElement("TD");
        var swChange = document.createTextNode("Last SW Change");
        td7.appendChild(swChange);
        tr.appendChild(td7);

        document.getElementById("head").appendChild(tr);
        systemCount++;
    }

    $('#tBody').empty();

    var tr = document.createElement("TR");

    var td1 = document.createElement("TD");
    var sysName = document.createTextNode(jsonData["sysName"]);
    td1.appendChild(sysName);
    tr.appendChild(td1);

    var td1 = document.createElement("TD");
    var sysDescr = document.createTextNode(jsonData["sysDescr"]);
    td1.appendChild(sysDescr);
    tr.appendChild(td1);

    var td1 = document.createElement("TD");
    var ipAdEntAddr = document.createTextNode(jsonData["ipAdEntAddr"]);
    td1.appendChild(ipAdEntAddr);
    tr.appendChild(td1);

    var td2 = document.createElement("TD");
    var sysLocation = document.createTextNode(jsonData["sysLocation"]);
    td2.appendChild(sysLocation);
    tr.appendChild(td2);

    var td3 = document.createElement("TD");
    var sysContact = document.createTextNode(jsonData["sysContact"]);
    td3.appendChild(sysContact);
    tr.appendChild(td3);

    var td4 = document.createElement("TD");
    var hrSystemUptime = document.createTextNode(jsonData["hrSystemUptime"]);
    td4.appendChild(hrSystemUptime);
    tr.appendChild(td4);

    var td5 = document.createElement("TD");
    var hrSystemProcesses = document.createTextNode(jsonData["hrSystemProcesses"]);
    td5.appendChild(hrSystemProcesses);
    tr.appendChild(td5);

    var td6 = document.createElement("TD");
    var hrPrinterStatus = document.createTextNode(jsonData["hrPrinterStatus"]);
    td6.appendChild(hrPrinterStatus);
    tr.appendChild(td6);

    var td7 = document.createElement("TD");
    var hrSWInstalledLastChange = document.createTextNode(jsonData["hrSWInstalledLastChange"]);
    td7.appendChild(hrSWInstalledLastChange);
    tr.appendChild(td7);

    document.getElementById("tBody").appendChild(tr);

    var table = $('#example').DataTable();
    var tt = new $.fn.dataTable.TableTools(table);

    $(tt.fnContainer()).insertBefore('div.dataTables_wrapper');
    $('div.DTTT_container').remove();
}