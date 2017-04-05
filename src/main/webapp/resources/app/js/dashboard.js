/**
 * Created by Kumar on 16-Mar-17.
 */
$(document).ready(function () {
    topRatedSongs();
    recommendedSongs();
    $('#loadingModal').modal('show');
    $.when(topRatedSongs(),recommendedSongs())
        .done(function (a1, a2) {
            loadData(a1[0]);
            loadRecommendedSongs(a2[0]);
            $('#loadingModal').modal('hide');
        })
});
var i = 1;
function topRatedSongs() {
    return $.ajax({
        type: "GET",
        url: "getSongsWithAverageRatings",
        dataType: "json"
    });
}
function recommendedSongs() {
    return $.ajax({
        type: "GET",
        url: "getRecommendation",
        dataType: "json"
    });d
}

function loadData(data) {
    var stmt = '';
   jQuery.each(data['Payload'], function (index, value) {
        stmt += '<div class="col-md-3 col-sm-3" style=" background-color: #003153; color: white ; margin-left:5px; width: 30%;border-radius: 25px;">' +
            '<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd; padding-left: -1px; margin-left: -30px;border-radius: 25px;">' +
            '<object data="resources/AlbumArt/'+value["movieId"]+'.jpg" width="304" height="236" style="max-width: 115%" type="image/jpg">'+
            '<img src="resources/AlbumArt/p1.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 115%"  >' +
            '</object>'+
            '</div>' +
            '<div class="col-md-6 col-sm-6">' +
            'Name: <span>' + value["name"] + '</span><br><br>' +
            '<span>' + averageStar(value["avgRating"]) + '</span>' +
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
        slidesToShow: 3,
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

function loadRecommendedSongs(data) {
    var stmt = '';
    jQuery.each(data['Payload'][0]['elements'], function (index, value) {
        stmt += '<div class="col-md-3 col-sm-3" style=" background-color: #003153; color: white ; margin-left:5px; width: 30%;border-radius: 25px;">' +
            '<div class="col-md-6 col-sm-6" style="border-right: thick double #ddd; padding-left: -1px; margin-left: -30px;border-radius: 25px;">' +
            '<object data="resources/AlbumArt/' + value["movieId"] + '.jpg" width="304" height="236" style="max-width: 115%" type="image/jpg">' +
            '<img src="resources/AlbumArt/p1.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 115%"  >' +
            '</object>' +
            '</div>' +
            '<div class="col-md-6 col-sm-6">' +
            'Name: <span>' + value["name"] + '</span><br><br>' +
            '</div>' +
            '</div>';
    });

    $('#recommendedSongs').append(stmt);
    $('#recommendedSongs').slick({
        dots: false,

        //autoplay: true,
        //autoplaySpeed:2000,
        arrows: true,
        infinite: false,
        speed: 300,
        slidesToShow: 3,
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
    var stmt = "<td>";
    for(var i=0; i< value ;i++){
     stmt+='<i style="color: yellow" class="fa fa-star fa-lg fa-fw"></i>';
    }
    stmt+='</td>';
    //stmt = '<span><form id="average-rating-form">' +
    //    '<span class="user-rating">' +
    //    '<input type="radio" name="average-ratings" id="5" ' + check(5, value) + ' value="5"><span class="star"></span>' +
    //    '<input type="radio" name="average-ratings" id="4" ' + check(4, value) + ' value="4"><span class="star"></span>' +
    //    '<input type="radio" name="average-ratings" id="3" ' + check(3, value) + ' value="3"><span class="star"></span>' +
    //    '<input type="radio" name="average-ratings" id="2" ' + check(2, value) + ' value="2"><span class="star"></span>' +
    //    '<input type="radio" name="average-ratings" id="1" ' + check(1, value) + ' value="1"><span class="star"></span>' +
    //    '</span>' +
    //    '</form> </span>';

    return stmt;
}
function check(val, value) {
    if (val == value) {
        return 'checked';
    }
}


//$('#test').rating('refresh', {
//    showClear: true,
//    disabled: !$('#test').attr('disabled')
//});