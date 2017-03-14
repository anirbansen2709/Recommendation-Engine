/**
 * Created by Anirban on 18/01/2017.
 */
$(document).ready(function () {
    listAllUsers();

});
var tableRow;
var table;
function listAllUsers() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "listAllUsers",
        success: function (data) {

            jQuery.each(data['Payload'], function (index, value) {
                tableRow='<tr>';
                jQuery.each(value, function (index, value) {
                       tableRow += '<td>'+value+'</td>';

                });
                tableRow += '</tr>';
                $('#users_table').append(tableRow);
            });
             table = $('#users_table').DataTable();
            new $.fn.dataTable.FixedHeader( table, {
                alwaysCloneTop: true
            });

        }, error: function (data, status) {
        }
    });
}
