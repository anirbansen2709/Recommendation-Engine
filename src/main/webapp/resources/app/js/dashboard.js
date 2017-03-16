/**
 * Created by Kumar on 16-Mar-17.
 */
$(document).ready(function () {
    topRatedSongs();
});
var i=1;
function topRatedSongs(){
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "getSongsWithAverageRatings",
        success: function (data) {
            loadData(data);
          }, error: function (data, status) {
        }
    });
}
function loadData(data){
    jQuery.each(data['Payload'], function (index, value) {

            $('#b' + i).text(value['movieId']+value['name']);
            i++;

            });


}
