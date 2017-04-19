var tableRow;
var table;
var mapOfSongs = {};
var responsiveHelper_datatable_tabletools1 = undefined;
var responsiveHelper_datatable_tabletools2 = undefined;
var responsiveHelper_datatable_tabletools3 = undefined;
var ifUserSelectedFlag = false;
var movieDetailsTable;
var breakpointDefinition = {
    tablet: 1024,
    phone: 480
};

$(document).ready(function () {
    $('#loadingModal').modal('show');

    $.ajax({
        type: "Get",
        url: "topRatedSongsChart",
        success: function (response) {
            response = jQuery.parseJSON(response);
            var chartChart = {
                "caption": "Top 5 Movies Based On Ratings",
                "subCaption": "On Basis of Ratings",
                "numberPrefix": "",
                "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
                "bgColor": "#ffffff",
                "showBorder": "0",
                "use3DLighting": "0",
                "showShadow": "0",
                "enableSmartLabels": "1",
                "startingAngle": "310",
                "showLabels": "0",
                "showPercentValues": "1",
                "showLegend": "1",
                "legendShadow": "0",
                "legendBorderAlpha": "0",
                "decimals": "2",
                "captionFontSize": "14",
                "subcaptionFontSize": "14",
                "toolTipColor": "#ffffff",
                "toolTipBorderThickness": "0",
                "toolTipBgColor": "#000000",
                "toolTipBgAlpha": "80",
                "toolTipBorderRadius": "2",
                "toolTipPadding": "5",
                "theme": "fint"
            }

            var renderId = "top-movies-based-on-count";
            var chartType = "doughnut3d";
            var chartName = "top-movies";
            simplechart(chartChart, response, chartType, renderId, chartName);
        },

        error: function (e) {
            alert("error");
        }

    });

    $.ajax({
        type: "Get",
        url: "ratingsWithCountChart",
        success: function (response) {
            response = jQuery.parseJSON(response);
//        console.log("1: "+response);
            var chartChart = {
                "caption": "Movies With Corresponding Ratings",
                "subCaption": "Count Values",
                "xAxisName": "Ratings",
                "yAxisName": "Count (In Numbers)",
                "numberSuffix": "",
                "theme": "fint",
                "showlabels ": "1",
                "showvalues ": "1",
                "showLegend ": "1",
                //"forceXAxisValueDecimals": "1",
                "xAxisValueDecimals": "0",
                "decimalPrecision": "2"
            };
            var chartType = "column3D";
            var renderId = "top-movies-based-on-ratings";
            var chartName = "top-ratings";

            simplechart(chartChart, response, chartType, renderId, chartName);


        },
        error: function (e) {
            alert("error");
        }
    });

    $.ajax({
        type: "Get",
        url: "getTopGenresWithCountChart",
        success: function (response) {
            response = jQuery.parseJSON(response);

            var chartChart = {

                "caption": "Count Of Every Genres",
                "subCaption": "Count values",
                "xAxisName": "Genres",
                "yAxisName": "Days",
                "lineThickness": "2",
                "paletteColors": "#0075c2",
                "baseFontColor": "#333333",
                "baseFont": "Helvetica Neue,Arial",
                "captionFontSize": "14",
                "subcaptionFontSize": "14",
                "showBorder": "0",
                "bgColor": "#ffffff",
                "showShadow": "1",
                //"canvasBgColor": "#ffffff",
                "canvasBorderAlpha": "1",
                "divlineAlpha": "100",
                "divlineColor": "#999999",
                "divlineThickness": "1",
                "divLineDashed": "1",
                "divLineDashLen": "1",
                "showXAxisLine": "1",
                "xAxisLineThickness": "1",
                "xAxisLineColor": "#999999",
                "showAlternateHGridColor": "1"

            };
            var chartType = "line";
            var renderId = "movies-based-on-date";
            var chartName = "top-genre";
            simplechart(chartChart, response, chartType, renderId, chartName);


        },
        error: function (e) {
            alert("error");
        }
    });
    $('#loadingModal').modal('hide');
});

function simplechart(chartChart, data, chartType, renderId, chartName) {
    var chartData = [];
    $.each(data.data, function (key, value) {
        if (chartName == "top-movies") {
            value["link"] = "j-showSelectedField-" + value["label"] + "";
        }
        else if (chartName == "top-ratings") {
            value["link"] = "j-showMovieDetails-" + value["label"] + "";
        } else if(chartName == "top-genre"){
            value["link"] = "j-showGenresDetails-" + value["label"] + "";
        }
        chartData.push({
            "label": value["label"],
            "value": value["value"].toFixed(2),
            "link": value["link"]
        });
    })

    var revenueChart = new FusionCharts({
        "type": chartType,
        "renderAt": renderId,
        "width": "100%",
        "height": "350%",
        "dataFormat": "json",
        "dataSource": {
            "chart": chartChart,
            "data": chartData
        }

    });
    revenueChart.render();
}

// ***********First Chart**************//


function showSelectedField(value) {
    $('#loadingModal').modal('show');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "userDetails?movieName=" + value,
        success: function (data) {
            loadTable1(data);
            $('#footer').css('position','static');
            $(".table-header").text("User Details of the Movie :-  ");
            $(".click-value").text(" ' "+value+" '");
            $('#loadingModal').modal('hide');
        }, error: function (data, status) {
        }
    });

}
function loadTable1(data) {

    $('#table2').addClass('display-none');
    $('#table3').addClass('display-none');
    $('#table1').removeClass('display-none');
    if (data['returnCode'] == '200') {
        movieDetailsTable = $('#user_Details_table').DataTable({
            "bLengthChange": false,
            //"bDestroy": true,
            "bRetrieve": true,
            "pageLength": 7,
            "columnDefs": [
                {className: "dt-body-right", "targets": []}/*,
                 {
                 "targets": [0],
                 "visible": false,
                 "searchable": false
                 }*/
            ],
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",

            "preDrawCallback": function () {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper_datatable_tabletools1) {
                    responsiveHelper_datatable_tabletools1 =
                        new ResponsiveDatatablesHelper($('#user_Details_table'), breakpointDefinition);
                }
            },
            "rowCallback": function (nRow) {
                responsiveHelper_datatable_tabletools1.createExpandIcon(nRow);
            },
            "drawCallback": function (oSettings) {
                responsiveHelper_datatable_tabletools1.respond();
            }
        });


        movieDetailsTable.clear();

        jQuery.each(data['Payload'], function (index, value) {
            var r = [];

            r[0] = value['userId'];
            r[1] = averageStar(value['rating']);
            r[2] = value['timestamp'];


            movieDetailsTable.row.add(r);

        });
        movieDetailsTable.draw();
    } else {
        showToastr(data['message'], 'Error', 'error');
    }


}
// ***********Second Chart**************//
//
function showMovieDetails(value) {
    $('#loadingModal').modal('show');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "moviesDetails?movieRating=" + value,
        success: function (data) {
            loadTable2(data);
            $('#footer').css('position','static');
            $(".table-header").text("Number Of Movies Having Rating :-  ");
            $(".click-value").text(" ' "+value+" '");
            $('#loadingModal').modal('hide');
        }, error: function (data, status) {
        }
    });
}
function loadTable2(data) {

    $('#table1').addClass('display-none');
    $('#table3').addClass('display-none');
    $('#table2').removeClass('display-none');;
    if (data['returnCode'] == '200') {
        movieDetailsTable = $('#movies_Details_table').DataTable({
            "bLengthChange": false,
            //"bDestroy": true,
            "bRetrieve": true,
            "pageLength": 7,
            "columnDefs": [
                {className: "dt-body-right", "targets": []}/*,
                 {
                 "targets": [0],
                 "visible": false,
                 "searchable": false
                 }*/
            ],
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",

            "preDrawCallback": function () {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper_datatable_tabletools2) {
                    responsiveHelper_datatable_tabletools2 =
                        new ResponsiveDatatablesHelper($('#movies_Details_table'), breakpointDefinition);
                }
            },
            "rowCallback": function (nRow) {
                responsiveHelper_datatable_tabletools2.createExpandIcon(nRow);
            },
            "drawCallback": function (oSettings) {
                responsiveHelper_datatable_tabletools2.respond();
            }
        });


        movieDetailsTable.clear();

        jQuery.each(data['Payload'], function (index, value) {
            var r = [];

            r[0] = value['movieId'];
            r[1] = value['name'];
            r[2] = value['noOfUsers'];
            r[3] = value['genres'];


            movieDetailsTable.row.add(r);

        });
        movieDetailsTable.draw();
    } else {
        showToastr(data['message'], 'Error', 'error');
    }


}

//**********Third Chart**********************

function showGenresDetails(value) {
    $('#loadingModal').modal('show');

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "genresDetails?genre=" + value,
        success: function (data) {
            loadGenreTable3(data);
            $('#footer').css('position','static');
            $(".table-header").text("Movies Details Having Genre :-  ");
            $(".click-value").text(" ' "+value+" '");
            $('#loadingModal').modal('hide');
        }, error: function (data, status) {
        }
    });
}
function loadGenreTable3(data) {
    $('#table1').addClass('display-none');
    $('#table2').addClass('display-none');
    $('#table3').removeClass('display-none');
    if (data['returnCode'] == '200') {

        genreDetailsTable = $('#genres_Details_table').DataTable({
            "bLengthChange": false,
            //"bDestroy": true,
            "bRetrieve": true,
            "pageLength": 7,
            "columnDefs": [
                {className: "dt-body-right", "targets": []}/*,
                 {
                 "targets": [0],
                 "visible": false,
                 "searchable": false
                 }*/
            ],
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",

            "preDrawCallback": function () {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper_datatable_tabletools3) {
                    responsiveHelper_datatable_tabletools3 =
                        new ResponsiveDatatablesHelper($('#genres_Details_table'), breakpointDefinition);
                }
            },
            "rowCallback": function (nRow) {
                responsiveHelper_datatable_tabletools3.createExpandIcon(nRow);
            },
            "drawCallback": function (oSettings) {
                responsiveHelper_datatable_tabletools3.respond();
            }
        });


        genreDetailsTable.clear();

        jQuery.each(data['Payload'], function (index, value) {
            var r = [];

            r[0] = value['movieId'];
            r[1] = value['name'];
            r[2] = averageStar(value['avgRating']);


            genreDetailsTable.row.add(r);

        });
        genreDetailsTable.draw();
    } else {
        showToastr(data['message'], 'Error', 'error');
    }


}

function averageStar(value) {
    var stmt = "<td>";
    for (var i = 0; i < value; i++) {
        stmt += '<i style="color: red; font-size: x-large;" class="fa fa-star fa-lg fa-fw"></i>';
    }
    stmt += '</td>';
    return stmt;
}
