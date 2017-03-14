<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/9/2016
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SNMP home</title>
<%--ajax--%>
  <script src="./resources/js/jquery-1.12.0.min.js" type="text/javascript"></script>
  <script type = "text/javascript" src = "http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

  <%--main ui --%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="./resources/css/animate.css" />
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/font-awesome/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/css/local.css" />
  <script type="text/javascript" src="./resources/main_ui/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="./resources/main_ui/bootstrap/js/bootstrap.min.js"></script>

  <script>
    function movetoNext(current, nextFieldID) {
      if (current.value.length >= current.maxLength) {
        document.getElementById(nextFieldID).focus();
      }
    }
    var Gcom,Gip;
    var total_ip_counter = 0;
    var panel_color = 0;
    var panel_id =[];
    function addIp(){
      var panel_primary="panel panel-primary";
      var panel_info="panel panel-info";
      var panel_success="panel panel-success";
      var panel_warning="panel panel-warning";
      var panel_danger="panel panel-danger";
      panel_id[total_ip_counter]=total_ip_counter;
      $('#ipList').append(' <div id="'+panel_id[total_ip_counter]+'"> ' +
                          '<div class = "panel-heading"> ' +
                             '<h4 class = "panel-title"> ' +
                             '<a data-toggle = "collapse" data-parent = "#accordion" href = "#collapseTwo">' +
                                '<p> IP: '+Gip+' Community: '+Gcom+' </p>' +
                             '</a>' +
                             '</h4>' +
                          ' </div> ' +

                          '<div id = "collapseTwo" class = "panel-collapse collapse"> ' +
                              '<div class = "panel-body">You can see live update of this device and history also :-) </div> ' +
                          '</div> ' +
                            '</div>');

              panel_color = panel_color%5;
              if(panel_color==0)      {      $('#0').addClass(panel_primary); panel_color++;}
              else if(panel_color==1) {      $('#1').addClass(panel_info); panel_color++;}
              else if(panel_color==2) {      $(panel_id[total_ip_counter]).addClass(panel_success); panel_color++;}
              else if(panel_color==3) {      $(panel_id[total_ip_counter]).addClass(panel_warning); panel_color++;}
              else                    {      $(panel_id[total_ip_counter]).addClass(panel_danger); panel_color++;}
              total_ip_counter ++;
    }

    function doLoginPost() {

      var ip1, ip2, ip3, ip4, community, flag = 1;

      ip1 = $('#ip1').val();
      ip2 = $('#ip2').val();
      ip3 = $('#ip3').val();
      ip4 = $('#ip4').val();
      community = $('#community').val();
      Gcom = community;
      if (!((ip1 > 0 && ip1 < 266 && ip1 != '') && (ip2 >= 0 && ip2 < 266 && ip2 != '') && (ip3 >= 0 && ip3 < 266 && ip3 != '') &&
              (ip4 >= 0 && ip4 < 266 && ip4 != ''))) {

        $("#wrongIp").modal("show");
        flag = 0;
      }
      if (community == '') {
        $('#wrongCommunity').modal("show");
        flag = 0;
      }
      if (flag == 1) {
        var ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;
        console.log("inside ipcompost.....ip :" + ip);
        Gip = ip;
        $.ajax({
          type: "Get",
          url: "home",
          data: "ip=" + ip + "&community=" + community,
          success: function (response) {

            console.log(response);
            $('#correct').modal("show");
            setTimeout(function(){
             addIp();
              $('#correct').modal('hide')
            }, 1500);

          },
          error: function (e) {
            alert('Error: ' + e);
          }
        });
      }
    }

  </script>


  <style>
    .form-control{
      font-size: 20px;
      padding:6px 24px ;
    }
    .input-group-addon
    {
      font-size: 30px;
      border:0px;
      color:blue;
      background-color: transparent;
    }
  </style>
</head>
<body>
<%--<center>--%>
  <%--<b>SNPM</b>--%>

<div id="wrapper">
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.html">Back to Admin</a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
      <ul class="nav navbar-nav side-nav">
        <li class="active"><a href="#"><i class="fa fa-list-ol"></i> Home</a></li>
        <li><a href="dashboard"><i class="fa fa-bullseye"></i> Dashboard</a></li>
        <li><a href="SnmpTable"><i class="fa fa-tasks"></i> SNMP Table</a></li>
        <li><a href="blog"><i class="fa fa-globe"></i> Blog</a></li>
        <li><a href="timeline"><i class="fa fa-font"></i> Timeline</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right navbar-user">
        <li class="dropdown messages-dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> Notifications <span class="badge">2</span> <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li class="dropdown-header">1 New Messages</li>
            <li class="message-preview">
              <a href="#">
                <span class="avatar"><i class="fa fa-bell"></i></span>
                <span class="message">Security alert</span>
              </a>
            </li>
            <li class="divider"></li>
            <li class="message-preview">
              <a href="#">
                <span class="avatar"><i class="fa fa-bell"></i></span>
                <span class="message">Security alert</span>
              </a>
            </li>
            <li class="divider"></li>
            <li><a href="#">Go to Inbox <span class="badge">2</span></a></li>
          </ul>
        </li>
        <li class="dropdown user-dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Rushil<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
            <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
            <li class="divider"></li>
            <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>

  <div id="page-wrapper">

    <div class="row">

      <div class="col-lg-12 text-center v-center">
        <p class="lead">Enter your IP and community to get SNMP details</p>
        <center>
          <form role="form">
            <div class="col-lg-10">
              <div class="form-group">

                <div class="input-group">
                  <input type="text" class="form-control inputs" name="InputName" id="ip1"
                         onkeyup="movetoNext(this, 'ip2')"
                         placeholder="255" maxlength="3" required><span class="input-group-addon"><span
                        style="font-weight: bold;">.</span></span>

                  <input type="text" class="form-control inputs" name="InputName" id="ip2"
                         onkeyup="movetoNext(this, 'ip3')"
                         placeholder="255" maxlength="3" required><span class="input-group-addon"><span
                        style="font-weight: bold;">.</span></span>

                  <input type="text" class="form-control inputs" name="InputName" id="ip3"
                         onkeyup="movetoNext(this, 'ip4')"
                         placeholder="255" maxlength="3" required><span class="input-group-addon"><span
                        style="font-weight: bold;">.</span></span>

                  <input type="text" class="form-control inputs" name="InputName" id="ip4"
                         onkeyup="movetoNext(this, 'community')"
                         placeholder="255" maxlength="3" required><span class="input-group-addon"><span
                        class="glyphicon glyphicon-asterisk"></span></span>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <input type="email" class="form-control" id="community" name="InputCommunity"
                         placeholder="Enter community name" required>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
              </div>

              <input type="button" name="submit" id="submit" value="Add new device"
                     class="btn btn-info pull-right"
                     onclick="doLoginPost();"/>
            </div>

            <br>
            <br>
            <br>
            <br>
            <br>
          </form>

        </center>

        <div class="col-lg-5 col-md-push-1">

          <div class="col-md-12">
            <div class="alert alert-success" id="success" style=" display:none">
              <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong>
            </div>
            <div class="alert alert-danger" id="failIp" style="display:none">
              <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check the ip .</strong>
            </div>
            <div class="alert alert-danger" id="failCommunity" style="display:none">
              <span class="glyphicon glyphicon-remove"></span><strong> Error! Please enter a community name
              .</strong>
            </div>
          </div>
        </div>

      </div>
    </div>
    <div class="col-lg-12 text-center v-center">
      <div class="col-lg-5 text-center v-center">

      <div id="ipList" class = "panel-group">

      </div>
    </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="text-center">
      <h1>Follow us</h1>
    </div>
    <div class="row">
      <div class="col-lg-12 text-center v-center" style="font-size: 39pt;">
        <a href="www.google.com"><span class="avatar"><i class="fa fa-google-plus"></i></span></a>
        <a href="www.linkedin.com"><span class="avatar"><i class="fa fa-linkedin"></i></span></a>
        <a href="www.facebook.com"><span class="avatar"><i class="fa fa-facebook"></i></span></a>
        <a href="www.github.com"><span class="avatar"><i class="fa fa-github"></i></span></a>
      </div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /#page-wrapper -->
</div>


<div class="modal fade" id="wrongIp" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Error</h4>
      </div>
      <div class="modal-body">
        <p>Asshole if u call this ip, u r in deep SHIT!!</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div class="modal fade" id="wrongCommunity" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Error</h4>
      </div>
      <div class="modal-body">
        <p>We need a commmunity name !!!</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div class="modal fade" id="correct" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">success</h4>
      </div>
      <div class="modal-body">
        <p> Thumbs up :) </p>
      </div>
    </div>

  </div>
</div>

</body>
</html>
