/**
 * Created by Kumar on 11-Apr-17.
 */

$(document).ready(function () {
    $('#loadingModal').modal('show');
    $('#loadingModal').modal('hide');
    $("#getRecommendation").removeClass('display-none');

});

$("#getRecommendation").click(function (e) {

    $("#getRecommendation").addClass('display-none');
    //$("#animate").show();
    //$("#load").show();
    $('#animation1').removeClass('display-none');
    $('#animation2').removeClass('display-none');

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
            $('#animation1').removeClass('display-none');
            $('#animation2').removeClass('display-none');
            //$("#animate").hide();
            //$("#load").hide();
            //$("#getRecommendation").show();
            $('#getRecommendation').removeClass('display-none');
        }, error: function (data, status) {
        }
    });
});
