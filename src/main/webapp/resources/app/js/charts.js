/**
 * Created by Anirban on 25/01/2017.
 */

    $(document).ready(function () {

        $.ajax({
            type: "Get",
            url: "topUsageCharts",
            success: function (response) {
                response = jQuery.parseJSON(response);
        var chartChart = {
                    "caption": "Split of Memory usage by Processes",
                    "subCaption": "Last year",
                    "numberPrefix": "$",
                    "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
                    "bgColor": "#ffffff",
                    "showBorder": "0",
                    "use3DLighting": "0",
                    "showShadow": "0",
                    "enableSmartLabels": "0",
                    "startingAngle": "310",
                    "showLabels": "0",
                    "showPercentValues": "1",
                    "showLegend": "1",
                    "legendShadow": "0",
                    "legendBorderAlpha": "0",
                    "decimals": "0",
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
                var renderId = "chart-container";
                var chartType = "doughnut3d";
                simplechart(chartChart,response,chartType,renderId);
            },
            error: function (e) {
                alert("error");
            }
        });

        $.ajax({
            type: "Get",
            url: "Upload",
            success: function (response) {
                response = jQuery.parseJSON(response);
//        console.log("1: "+response);
                var chartChart = {"caption": "Top uploading PCs",
                    "subCaption": "with their MAC",
                    "xAxisName": "MAC - Address",
                    "yAxisName": "Upload  (In GB)",
                    "numberSuffix": " GB",
                    "theme": "fint",
                    "showlabels ": "1",
                    "showvalues ": "1",
                    "showLegend ": "1"
                };
                var chartType = "column3D";
                var renderId = "chartContainerUpload";
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
                    "caption": "Top downloading PCs",
                    "subCaption": "with their MAC",
                    "xAxisName": "MAC - Address",
                    "yAxisName": "Size  (In GB)",
                    "numberSuffix": " GB",
                    "showlabels ": "1",
                    "showvalues ": "1",
                    "theme": "fint",
                    "showLegend ": "1",
                };
                var chartType = "column3D";
                var renderId = "chart-container-download";
//        simplechart(chart,data,chartType,renderId);
                simplechart(chartChart,response,chartType,renderId);


            },
            error: function (e) {
                alert("error");
            }
        });
    });

