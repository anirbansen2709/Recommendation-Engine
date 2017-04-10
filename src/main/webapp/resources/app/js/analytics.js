
$(document).ready(function () {
    function simplechart(chartChart,data, chartType, renderId) {
        var chartData = [];
        $.each(data.data, function (key, value) {
            chartData.push({
                "label": value["label"],
                "value": value["value"].toFixed(2)
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
                "subcaptionFontBold": "0",
                "toolTipColor": "#ffffff",
                "toolTipBorderThickness": "0",
                "toolTipBgColor": "#000000",
                "toolTipBgAlpha": "80",
                "toolTipBorderRadius": "2",
                "toolTipPadding": "5",

            };
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

// download chart
    $.ajax({
        type: "Get",
        url: "Download",
        success: function (response) {
            response = jQuery.parseJSON(response);
//        console.log("1: "+response);
            var chartChart = {
                //"caption": "Last 10 Days History Of User",
                //"subCaption": "with their MAC",
                //"xAxisName": "Days",
                //"yAxisName": "Count (In Numbers)",
                //"numberSuffix": " GB",
                //"showlabels ": "1",
                //"showvalues ": "1",
                //"theme": "fint",
                //"showLegend ": "1",


                "chart": {
                    "caption": "Last 10 Days History Of User",
                    "subCaption": "Last week",
                    "xAxisName": "Day",
                    "yAxisName": "Days",
                    "lineThickness": "2",
                    "paletteColors": "#0075c2",
                    "baseFontColor": "#333333",
                    "baseFont": "Helvetica Neue,Arial",
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0",
                    "showBorder": "0",
                    "bgColor": "#ffffff",
                    "showShadow": "0",
                    "canvasBgColor": "#ffffff",
                    "canvasBorderAlpha": "0",
                    "divlineAlpha": "100",
                    "divlineColor": "#999999",
                    "divlineThickness": "1",
                    "divLineDashed": "1",
                    "divLineDashLen": "1",
                    "showXAxisLine": "1",
                    "xAxisLineThickness": "1",
                    "xAxisLineColor": "#999999",
                    "showAlternateHGridColor": "0"
                },
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

