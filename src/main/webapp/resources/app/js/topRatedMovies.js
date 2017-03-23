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
                loadSongsWithGenre(data);
                $('#loadingModal').modal('hide');

            }, error: function (data, status) {
            }
        });
 }

function loadSongsWithGenre(data) {
    var stmt = '';
    jQuery.each(data['Payload'], function (index, value) {
        stmt += '<div  id ="'+value["key"]+' "  class="col-md-2 col-sm-2 criteriaBasedClick" style=" background-color: #003153; color: white ; margin-left:5px; width: 30%;border-radius: 25px;">' +
            '<div class="col-md-12 col-sm-12" style="border-right: thick double #ddd; padding-left: -1px; margin-left: -30px;border-radius: 25px;">' +
            '<img src="resources//GenresImages//'+value["key"]+'.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 115%">' +
            '</div>' +
            '</div>';
    });
    $('#topXRatedSongs').append(stmt);

    $('#topXRatedSongs').slick({
        dots: false,

        //autoplay: true,
        //autoplaySpeed:2000,
        arrows: true,
        infinite: false,
        speed: 300,
        slidesToShow:6,
        slidesToScroll: 3,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    infinite: true,
                    arrows: true,
                    dots: false
                }
            },
            {
                breakpoint: 1100,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    infinite: true,
                    arrows: true,
                    dots: false
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    arrows: true,
                    dots: false
                }
            },
            {
                breakpoint: 320,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    arrows: true,
                    dots: false
                }
            }

        ]
    });
}
function averageStar(value) {
    var stmt = "";
    stmt = '<span><form id="average-rating-form">' +
        '<span class="user-rating">' +
        '<input type="radio" name="average-ratings" id="5" ' + check(5, value) + ' value="5"><span class="star"></span>' +
        '<input type="radio" name="average-ratings" id="4" ' + check(4, value) + ' value="4"><span class="star"></span>' +
        '<input type="radio" name="average-ratings" id="3" ' + check(3, value) + ' value="3"><span class="star"></span>' +
        '<input type="radio" name="average-ratings" id="2" ' + check(2, value) + ' value="2"><span class="star"></span>' +
        '<input type="radio" name="average-ratings" id="1" ' + check(1, value) + ' value="1"><span class="star"></span>' +
        '</span>' +
        '</form> </span>';
    return stmt;
}
function check(val, value) {
    if (val == value) {
        return 'checked';
    }
}


$('#topXRatedSongs').on('click','.criteriaBasedClick', function(){
    alert(this.id);
});