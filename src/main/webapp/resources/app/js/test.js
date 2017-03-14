/**
 * Created by Anirban on 08-Mar-17.
 */
$(document).ready(function () {
    //rating();

        $('#selected-rating').text($('[name="rating"]:checked').val());

});

$('#user-rating-form').on('change','[name="rating"]',function(){
    $('#selected-rating').text($('[name="rating"]:checked').val());
});