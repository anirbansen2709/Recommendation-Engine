/**
 * Created by Kumar on 11-Apr-17.
 */

$(document).ready(function () {
    $("#animate").hide();
    $("#load").hide();

});


$("#getRecommendation").click(function (e) {
    $("#getRecommendation").hide();
    $("#animate").show();
    $("#load").show();

    var originalText = $("#loading").text(),
        i  = 0;
    setInterval(function() {

        $("#loading").append(".");
        i++;

        if(i == 4)
        {
            $("#loading").html(originalText);
            i = 0;
        }

    }, 500);

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "getRecommendationButton",
        success: function (data) {
            $("#animate").hide();
            $("#load").hide();
            $("#getRecommendation").show();

        }, error: function (data, status) {
        }
    });
});
