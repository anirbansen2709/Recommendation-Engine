<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<!-- Main section-->
<section>
  <!-- Page content-->
  <div class="content-wrapper">
    <div class="content-heading">
      Dashboard
      <small data-localize="dashboard.WELCOME"></small>
    </div>

    <div class="row mb-lg">
      <!-- START panel-->
      <div id="panelDemoRefresh" class="panel panel-default panel-demo col-lg-6" >
        <div class="panel-heading">Top PCs Downloading Data
          <a href="#" data-tool="panel-dismiss" data-toggle="tooltip" title="Close Panel" class="pull-right">
            <em class="fa fa-times"></em>

          </a>
          <a href="#" data-tool="panel-refresh" data-toggle="tooltip" title="Refresh Panel" data-spinner="line back-and-forth grow" class="pull-right">
            <em class="fa fa-refresh"></em>
          </a>
          <a href="#" data-tool="panel-collapse" data-toggle="tooltip" title="Collapse Panel" class="pull-right">
            <em class="fa fa-minus"></em>
          </a>

        </div>
        <div class="panel-body">
          <div id='chart-container'>
            FusionCharts will load here!
          </div>
        </div>
      </div>
      <!-- END panel-->
      <!-- START panel-->
      <div id="panelDemoRefresh3" class="panel panel-default panel-demo col-lg-6">
        <div class="panel-heading">Top 10 Uploading PCs
          <a href="#" data-tool="panel-dismiss" data-toggle="tooltip" title="Close Panel" class="pull-right">
            <em class="fa fa-times"></em>
          </a>
          <a href="#" data-tool="panel-refresh" data-toggle="tooltip" title="Refresh Panel" data-spinner="line back-and-forth grow" class="pull-right">
            <em class="fa fa-refresh"></em>
          </a>
          <a href="#" data-tool="panel-collapse" data-toggle="tooltip" title="Collapse Panel" class="pull-right">
            <em class="fa fa-minus"></em>
          </a>

        </div>
        <div class="panel-body">

          <div id="chartContainerUpload">
            FusionCharts will load here!
          </div>
        </div>
      </div>
      <!-- END panel-->
    </div>



  </div>
</section>
<%--this will import footer and all the commom js--%>
<%--imports for fusion charts--%>
<script src="./resources/fusionCharts/jquery.min.js"></script>
<script src="./resources/fusionCharts/fusion/FusionCharts.js"></script>
<script src="./resources/fusionCharts/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script src="./resources/app/js/charts.js"></script>
<script src="./resources/app/js/topUsageCharts.js"></script>
<%@ include file="./uiFrameFooter.jsp" %>
</body>

<script>
  function simplechart(chartChart,data,chartType,renderId){
    var chartData = [];
    $.each(data.data, function(key, value){
      chartData.push({
        "label": value["label"],
        "value": value["value"].toFixed(2)
      });
    });
    var revenueChart = new FusionCharts({
      "type": chartType,
      "renderAt": renderId,
      "width": "100%",
      "height": "350",
      "dataFormat": "json",
      "dataSource": {
        "chart": chartChart,
        "data" : chartData
      }

    });
    revenueChart.render();
  }
</script>


</html>