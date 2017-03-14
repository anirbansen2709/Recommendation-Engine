/**
 * Created by Anirban on 07-Mar-17.
 */
/**
 * Created by Anirban on 18/01/2017.
 */
$(document).ready(function () {
    listAllSongs();
    saveRatings();
});
function saveRatings() {
    $('#user-rating-form').on('change', '[name="rating"]', function () {
        alert("hello");
        //$('#selected-rating').text($('[name="rating"]:checked').val());
    });
}
var tableRow;
var table;
var mapOfSongs={}
var responsiveHelper_datatable_tabletools = undefined;

var breakpointDefinition = {
    tablet : 1024,
    phone : 480
};
function listAllSongs() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "getSongsWithAverageRatings",
        success: function (data) {
                    loadTable(data);


        }, error: function (data, status) {
        }
    });
}

function loadTable(data){
    if(data['returnCode'] == '200') {
        var songTable= $('#songs_table').DataTable({
            "bLengthChange": false,
            "pageLength":15,
            "columnDefs": [
                { className: "dt-body-right", "targets": [] }
            ],
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>"+
            "t"+
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",

            "preDrawCallback" : function() {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper_datatable_tabletools) {
                    responsiveHelper_datatable_tabletools =
                        new ResponsiveDatatablesHelper($('#songs_table'), breakpointDefinition);
                }
            },
            "rowCallback" : function(nRow) {
                responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
            },
            "drawCallback" : function(oSettings) {
                responsiveHelper_datatable_tabletools.respond();
            }
        });

        songTable.clear();

        jQuery.each(data['Payload'], function (index, value) {
            var r = [];

            r[0] = value['name'];
            r[1] = averageStar(value['avgRating']);
            r[2] = userStar(value['name']);


            songTable.row.add(r);
        });

        songTable.draw();
    }else{
        showToastr(data['message'], 'Error', 'error');
    }
}

function averageStar(value){
    var stmt ="";
stmt ='<span><form id="average-rating-form">'+
    '<span class="user-rating">'+
        '<input type="radio" name="average-ratings" id="5" '+check(5,value)+' value="5"><span class="star"></span>'+
        '<input type="radio" name="average-ratings" id="4" '+check(4,value)+' value="4"><span class="star"></span>'+
        '<input type="radio" name="average-ratings" id="3" '+check(3,value)+' value="3"><span class="star"></span>'+
        '<input type="radio" name="average-ratings" id="2" '+check(2,value)+' value="2"><span class="star"></span>'+
        '<input type="radio" name="average-ratings" id="1" '+check(1,value)+' value="1"><span class="star"></span>'+
    '</span>'+
    '</form> </span>';
    return stmt;
}
function check(val,value)
{
    if(val==value)
    {
       return 'checked';
    }
}
function userStar(songName){
    var stmt ="";
    stmt ="<span><div class='panel-body'>"+
          "<form id='user-rating-form'>"+
          "<span class='user-rating'>"+
          "<input type='radio' name='user-ratings' value='"+ songName +"~5'><span class='star'></span>"+
          "<input type='radio' name='user-ratings' value='"+ songName +"~4'><span class='star'></span>"+
          "<input type='radio' name='user-ratings' value='"+ songName +"~3'><span class='star'></span>"+
          "<input type='radio' name='user-ratings' value='"+ songName +"~2'><span class='star'></span>"+
          "<input type='radio' name='user-ratings' value='"+ songName +"~1'><span class='star'></span>"+
          "</span>"+
          "</form> </div> </span> ";
    return stmt;
}
$("#songs_table").on('click', 'input[name=user-ratings]', function(e){
    var value = $(this).val();
    var songname = value.split('~')[0];
    var rating = value.split('~')[1];
    mapOfSongs[songname]=rating;

})

$("#saveRatings").click(function (e) {
    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "saveRatings",
        data: JSON.stringify(mapOfSongs),
        dataType: "json",
        success: function (data, status) {
            if (data['returnCode'] == '200') {
                alert("Entry was successful")
            }
            else {
                alert("Entry was not succesful");
            }
        }, error: function (xhr) {
        }
    });

});
