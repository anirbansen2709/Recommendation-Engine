/**
 * Created by Kumar on 11-Apr-17.
 */
$("#getRecommendation").click(function (e) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "getRecommendationButton",
        success: function (data) {
            loadTable(data);
            $('#loadingModal').modal('hide');

        }, error: function (data, status) {
        }
    });
});
