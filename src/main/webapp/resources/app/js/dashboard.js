/**
 * Created by Kumar on 16-Mar-17.
 */
$(document).ready(function () {
    topRatedSongs();
});
var i = 1;
function topRatedSongs() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "getSongsWithAverageRatings",
        success: function (data) {
            loadData(data);
        }, error: function (data, status) {
        }
    });
}
function loadData(data) {
    var stmt = '';
    jQuery.each(data['Payload'], function (index, value) {
            stmt += '<div class="col-md-3 col-sm-3" style=" background-color: #eee; margin-left:5px; width: 30%;">' +
            '<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd; padding-left: -1px; margin-left: -30px">' +
            '<img src="resources/AlbumArt/'+value["movieId"]+'.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 115%">' +
            '</div>' +
            '<div class="col-md-6 col-sm-6">' +
            'Name: <span>' + value["name"] + '</span>' +
            '<span>' + averageStar(value["avgRating"]) + '</span>' +
            '</div>' +
            '</div>';
    });
    $('#topXRatedSongs').append(stmt);

    $('#topXRatedSongs').slick({
        dots: false,

        arrows: true,
        infinite: false,
        speed: 300,
        slidesToShow: 3,
        slidesToScroll: 3,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
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