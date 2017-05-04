/**
 * Created by Anirban on 03-May-17.
 */
$(document).ready(function () {
    showUsername();
});
function showUsername(){

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "currentUser",
        success: function (data) {
        console.log(data);
            $("#userName").text(data["Payload"][0]["Username"]);
        }, error: function (data, status) {
        }
    });
}