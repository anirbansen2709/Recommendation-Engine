/**
 * Created by Debashish Sen on 20-Mar-17.
 */

$(document).ready(function () {
    topRatedMovies();
});

function topRatedMovies() {

        $('#loadingModal').modal('show');
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "getSongsWithGenres",
            success: function (data) {
                console.log(data['Payload']);
                $('#loadingModal').modal('hide');

            }, error: function (data, status) {
            }
        });
 }