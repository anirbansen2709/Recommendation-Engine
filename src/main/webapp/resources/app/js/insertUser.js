/**
 * Created by Anirban on 19/01/2017.
 */

/**
 * Created by Anirban on 18/01/2017.
 */
$(document).ready(function () {
    insertUser();
});
var xmlRequestObject = new createXmlHttpRequestObject();
function insertUser() {
    $("#addUserButton").click(function (e) {

        e.preventDefault();

        var userName = $("#userid_admin").val();
        var password = $("#password_admin").val();
        var confirmPassword = $("#passwordagain_admin").val();
        var firstName = $("#username").val();
        var eMail = $("#email").val();

        $.ajax({
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "insertUser",
            data: JSON.stringify({
                'username': userName,
                'email': eMail,
                'password': password,
                'firstName': firstName
            }),
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
}
