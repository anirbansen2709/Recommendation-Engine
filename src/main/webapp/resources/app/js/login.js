/**
 * Created by Anirban on 14-Apr-17.
 */
$(document).ready(function(){
    $("#button").click(function () {
        // Set the effect type
        var effect = 'slide';

        // Set the options for the effect type chosen
        var options = { direction: 'down' };

        // Set the duration (default: 400 milliseconds)
        var duration = 500;
        $('.header-content').css({
            "top":"0%",
            "left":"26%"
        })
        $('#homeHeading').css({
            font:"400 70px / 1.3 'Lobster Two', Helvetica, sans-serif"
    })

        $('#button').hide(500);
        $('#userform').show(500);

        });

    loginUser();
    signupUser();
    });
$('#form').find('input, textarea').on('keyup blur focus', function (e) {
    var $this = $(this),
        label = $this.prev('label');
    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if( $this.val() === '' ) {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {
        if( $this.val() === '' ) {
            label.removeClass('highlight');
        }
        else if( $this.val() !== '' ) {
            label.addClass('highlight');
        }
    }
});

$('.tab a').on('click', function (e) {
    e.preventDefault();
    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');
    target = $(this).attr('href');
    $('.tab-content > div').not(target).hide();
    $(target).fadeIn(800);
});
function Redirect() {
    window.location="/home";
}


function loginUser() {
    $("#userLogin").click(function (e) {

        e.preventDefault();

        var password = $("#password1").val();
        var emailId = $("#email1").val();

        $.ajax({
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "userLogin",
            data: JSON.stringify({
                'emailId': emailId,
                'password': password,
            }),
            dataType: "json",
            success: function (data, status) {
                if (data['returnCode'] == '200') {
                    Redirect();

                }
                else {
                    alert("Try Again! EmailId/Password not matched");
                }
            }, error: function (xhr) {
            }
        });
    });
}
function signupUser() {
    $("#userSignup").click(function (e) {

        e.preventDefault();

        var password = $("#password").val();
        var emailId = $("#email").val();
        var firstName = $("#first_name").val();
        var lastName = $("#last_name").val();
        $.ajax({
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "userSignup",
            data: JSON.stringify({
                'emailId': emailId,
                'password': password,
                'firstName': firstName,
                'lastName': lastName
            }),
            dataType: "json",
            success: function (data, status) {
                if (data['returnCode'] == '200') {
                    alert("Signup Successful");

                }
                else {
                    alert("Try Again! Error");
                }
            }, error: function (xhr) {
            }
        });
    });
}