var tableRow;
var table;
var mapOfSongs = {};
var responsiveHelper_datatable_tabletools = undefined;
var ifUserSelectedFlag = false;
var movieDetailsTable;
var breakpointDefinition = {
    tablet: 1024,
    phone: 480
};

$(document).ready(function () {
    function simplechart(chartChart,data, chartType, renderId) {
        var chartData = [];
        $.each(data.data, function (key, value) {
            chartData.push({
                "label": value["label"],
                "value": value["value"].toFixed(2),
                //"link": "j-showSelectedField-"+value["label"]+"",
                "link": "j-showMovieDetails-"+value["label"]+""
            });
        });
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
            simplechart(chartChart,response,chartType,renderId);
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
            var chartChart = {"caption": "Movies With Corresponding Ratings",
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
                "decimalPrecision":"2"
            };
            var chartType = "column3D";
            var renderId = "top-movies-based-on-ratings";
//        simplechart(chart,data,chartType,renderId);
            simplechart(chartChart,response,chartType,renderId);


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
            //$.each(response["Payload"] ,function(index, value){
            //
            //});
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
//        simplechart(chart,data,chartType,renderId);
            simplechart(chartChart,response,chartType,renderId);


        },
        error: function (e) {
            alert("error");
        }
    });
});
// ***********First Chart**************//


//function showSelectedField(value){
//    $.ajax({
//        type: "GET",
//        dataType: "json",
//        url: "userDetails?movieName="+value,
//        success: function (data) {
//            loadTable(data);
//        }, error: function (data, status) {
//        }
//    });
//}
//function loadTable(data) {
//
//
//    if (data['returnCode'] == '200') {
//        movieDetailsTable = $('#user_Details_table').DataTable({
//            "bLengthChange": false,
//            //"bDestroy": true,
//            "bRetrieve": true,
//            "pageLength": 7,
//            "columnDefs": [
//                {className: "dt-body-right", "targets": []}/*,
//                {
//                    "targets": [0],
//                    "visible": false,
//                    "searchable": false
//                }*/
//            ],
//            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-12'f>" +
//            "t" +
//            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
//
//            "preDrawCallback": function () {
//                // Initialize the responsive datatables helper once.
//                if (!responsiveHelper_datatable_tabletools) {
//                    responsiveHelper_datatable_tabletools =
//                        new ResponsiveDatatablesHelper($('#user_Details_table'), breakpointDefinition);
//                }
//            },
//            "rowCallback": function (nRow) {
//                responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
//            },
//            "drawCallback": function (oSettings) {
//                responsiveHelper_datatable_tabletools.respond();
//            }
//        });
//
//
//        movieDetailsTable.clear();
//
//        jQuery.each(data['Payload'], function (index, value) {
//            var r = [];
//
//            r[0] = value['userId'];
//            r[1] = averageStar(value['rating']);
//            r[2] = value['timestamp'];
//
//
//            movieDetailsTable.row.add(r);
//
//        });
//        movieDetailsTable.draw();
//    } else {
//        showToastr(data['message'], 'Error', 'error');
//    }
//
//
//}
// ***********Second Chart**************//

function showMovieDetails(value){
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "moviesDetails?movieRating="+value,
        success: function (data) {
            loadTable(data);
        }, error: function (data, status) {
        }
    });
}
function loadTable(data) {


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
                if (!responsiveHelper_datatable_tabletools) {
                    responsiveHelper_datatable_tabletools =
                        new ResponsiveDatatablesHelper($('#movies_Details_table'), breakpointDefinition);
                }
            },
            "rowCallback": function (nRow) {
                responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
            },
            "drawCallback": function (oSettings) {
                responsiveHelper_datatable_tabletools.respond();
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

//function averageStar(value) {
//    var stmt = "<td>";
//    for(var i=0; i< value ;i++){
//        stmt+='<i style="color: red; font-size: x-large;" class="fa fa-star fa-lg fa-fw"></i>';
//    }
//    stmt+='</td>';
//    return stmt;
//}