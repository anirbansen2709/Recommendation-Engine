/**
 * Created by Debashish Sen on 20-Mar-17.
 */
var lastGenre;
var mapOfGenres={};
$(document).ready(function () {

    $('#listOfMovies').empty();
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
    var genre = '';

    jQuery.each(data['Payload'], function (index, value) {
        stmt += '<div id ="'+value["key"]+'" class="col-xs-6 col-sm-6 col-md-12 col-lg-12 criteriaBasedClick" style="cursor: pointer">'+
            '<div class="widget bg-red">'+
            '<div class="row row-table clickedGenre" id ="'+value["key"]+'clickedGenre" style="background-color: brown;color: white ;border-radius: 25px">'+
            '<div class="col-xs-8 pv-lg">'+
            '<div class="text-uppercase" style="font-size: 127%;margin-left: -13px; text-align: center;"><b>'+value.key+'</b></div>'+
            '</div>'+
            '</div>'+
            '</div>'+
            '</div>';
               mapOfGenres[value["key"]] = value["value"]

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
    //displayMovies(mapOfGenres);
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
//function displayMovies(mapOfGenres) {
//    console.log(mapOfGenres)
//    }
$('#topXRatedSongs').on('click','.criteriaBasedClick', function() {
    //$('.clickedGenre').css('backgroundColor','brown');
    $('#'+lastGenre).css('backgroundColor','brown');
    var genre;
    var stmt;
    var count=0;
    console.log(mapOfGenres);
    genre = $(this).attr('id');
    $('#'+genre+'clickedGenre').css('backgroundColor','#003153')
    lastGenre = genre+'clickedGenre';
    $('#listOfMovies').empty();
    stmt = '';

    jQuery.each(mapOfGenres[genre], function (index, value) {
        stmt+='<div id= '+value["name"]+' style="margin-bottom: 50px !important;" class="col-sm-2">'+
            '<div style="border:double">' +
            '<object data="resources/AlbumArt/'+value["movieId"]+'.jpg" width="304px" height="236px" style="max-width: 100%; height: 170px;" type="image/jpg">'+
            '<img src="resources/AlbumArt/p1.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 100%; height: 170px;"  >' +
            '</object>'+
            '</div>'+
            '<div style="border:double; height: 55px; min-height: 55px">' +
            '<span>' + value["name"] + '</span><br><br>' +
            '</div>'+
            '</div>'
    });
    $('#listOfMovies').append(stmt);

});

