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
