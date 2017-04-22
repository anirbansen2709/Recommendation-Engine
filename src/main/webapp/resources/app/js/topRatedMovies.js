/**
 * Created by Debashish Sen on 20-Mar-17.
 */
var genre;
var track_page = 1; //track user scroll as page number, right now page number is 1
var loading  = false;
var lastGenre;
var mapOfGenres={};
$(document).ready(function () {
    $('.loading-info').hide();
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
            '<div class="row row-table clickedGenre" id ="'+value["key"]+'clickedGenre" style="color: white ; padding-top: 15px;">'+
            '<img src="resources/genres/'+value["key"]+'.jpg" class="img-responsive genre" alt="" width="304" height="236" style="max-width: 100%; height: 170px;"  >' +
            //'<div class="col-xs-8 pv-lg">'+
            //'<div class="text-uppercase" style="font-size: 127%;margin-left: -13px; text-align: center;"><b>'+value.key+'</b></div>'+
            //'</div>'+
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
    $('#'+lastGenre).css('backgroundColor','powderblue');
    var count=0;
    console.log(mapOfGenres);
    genre = $(this).attr('id');
    $('#'+genre).css('backgroundColor','#003153')
    lastGenre = genre;
    $('#listOfMovies').empty();
    divCreation(mapOfGenres[genre]);
});
    function divCreation(data){
        var stmt='';
    jQuery.each(data, function(index
        , value) {
        stmt+='<div id= '+value["name"]+' style="margin-bottom: 50px !important;" class="col-sm-2">'+
            '<div>' +
            '<object data="resources/AlbumArt/'+value["movieId"]+'.jpg" width="304px" height="236px" style="max-width: 100%; height: 170px;" type="image/jpg">'+
            '<img src="resources/AlbumArt/p1.jpg" class="img-responsive" alt="" width="304" height="236" style="max-width: 100%; height: 170px;"  >' +
            '</object>'+
            '</div>'+

            '<div class= "card-name" style=" height: 50px; min-height: 50px;text-align: center">' +
            '<span>' + value["name"]  + '</span><br>' +
            '</div>'+
            '<div class= "card-body" style=" height: 25px; text-align: center">' +
            '<span>' + averageStar(value["avgRating"])  + '</span><br>' +
            '</div>'+
            '</div>'
    });
    $('#listOfMovies').append(stmt);

}
function averageStar(value) {
    var stmt = "<td>";
    for (var i = 0; i < value; i++) {
        stmt += '<i style="color: red; font-size: x-large;" class="fa fa-star fa-lg fa-fw"></i>';
    }
    stmt += '</td>';
    return stmt;
}
$(window).scroll(function() { //detect page scroll
    if($(window).scrollTop() + $(window).height() >= $(document).height()) { //if user scrolled to bottom of the page
        track_page++; //page number increment
        load_contents(track_page); //load content
    }
});
function load_contents(track_page) {
    if (loading == false) {
        loading = true;  //set loading flag on
        $('.loading-info').show(); //show loading animation
        $.ajax({
            type: "GET",
            dataType: "json",
            async: false,
            url: "getMoreMovies?track_page="+track_page+"&genre="+genre,
            success: function (data) {
                loading = false; //set loading flag off once the content is loaded
                divCreation(data["Payload"]);
                $('.loading-info').hide(); //hide loading animation once data is received//app
                loading == false;
            }, error: function (data, status) {
                if(data["message"]!=null){
                        $(".noMoreRecords").append(data["message"])
                }
                loading == false;
            }
        });
    }
}
