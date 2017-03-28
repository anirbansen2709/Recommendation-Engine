/**
 * Created by Anirban on 24-Mar-17.
 */
var tableRow;
var table;
var mapOfSongs = {};
var responsiveHelper_datatable_tabletools = undefined;
var ifUserSelectedFlag = false;
var breakpointDefinition = {
    tablet: 1024,
    phone: 480
};
$(document).ready(function () {
    listRatedSongs();

});

function listRatedSongs() {
    $('#loadingModal').modal('show');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "listRatedSongs",
        success: function (data) {
            loadTable(data);
            $('#loadingModal').modal('hide');

        }, error: function (data, status) {
        }
    });
}
function loadTable(data) {
    if (data['returnCode'] == '200') {
        var history_table = $('#history_table').DataTable({
            "bLengthChange": false,
            "pageLength": 7,
            "columnDefs": [
                {className: "dt-body-right", "targets": []},
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ],
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",

            "preDrawCallback": function () {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper_datatable_tabletools) {
                    responsiveHelper_datatable_tabletools =
                        new ResponsiveDatatablesHelper($('#history_table'), breakpointDefinition);
                }
            },
            "rowCallback": function (nRow) {
                responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
            },
            "drawCallback": function (oSettings) {
                responsiveHelper_datatable_tabletools.respond();
            }
        });

        history_table.clear();

        jQuery.each(data['Payload'], function (index, value) {
            var r = [];

            r[0] = value['movieId'];
            r[1] = value['name'];
            r[2] = value['genres'];
            r[3] = averageStar(value['rating']);
            r[4] = value['timestamp'];


           history_table.row.add(r);
        });
        history_table.draw();
    } else {
        showToastr(data['message'], 'Error', 'error');
    }
}
function averageStar(value) {
    var stmt = "<td>";
    for(var i=0; i< value ;i++){
        stmt+='<i style="color: red; font-size: x-large;" class="fa fa-star fa-lg fa-fw"></i>';
    }
    stmt+='</td>';
    return stmt;
}
