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
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/font-awesome/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="./resources/main_ui/css/local.css" />
  <script type="text/javascript" src="./resources/main_ui/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="./resources/main_ui/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<%--<center>--%>
  <%--<b>SNPM</b>--%>
  <script>
    var community;
    function setCommunityPublic(){
      community = $('#public').val();
    }
    function setCommunityPrivate(){
      community = $('#private').val();
    }
    function setCommunity_name(){
      community = $('#community_name').val();
    }


    function doAjaxPost() {


      var name = $('#name').val();
      $.ajax({
        type : "Get",
        url : "home",
        data : "name=" + name + "&community=" + community,
        success : function(response) {
        //    windows.open("SnmpTable");

        },
        error : function(e) {
          alert('Error: ' + e);
        }
      });
//        windows.open("SnmpTable");

//      $('').load('dashboard');

    }
  </script>

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

        <h1>Get details</h1>
        <p class="lead">Enter your IP and community to get SNMP details</p>

        <br>
        <br>
        <br>

        <form class="col-lg-12">
          <div class="input-group" style="width: 400px; text-align: center; margin: 0 auto;">
            <table>

            <tr>
              <td>
                    <input class="form-control input-lg" title="Confidential." placeholder="Enter your IP" type="text" id="name">
                    <span class="input-group-btn"></span>
              </td>
              <td>
                <a href="SnmpTable">
                <button class="btn btn-lg btn-primary" type="button" onclick="doAjaxPost();"> OK</button>
                </a>
              </td>
              </tr>
            </table>
              <table>
              <br>
              <tr>
                <td><b>Community :</b><input type="radio" id="public" value="public" onclick="setCommunityPublic()"/>  public
                  <input type="radio" id="private" value="private" onclick="setCommunityPrivate()"/> private</td>
              <td>
                <input title="optional" placeholder="  Or Else" type="text" id="community_name" onchange="setCommunity_name()"/>
              </td>

              </tr>

            </table>
          </div>
        </form>
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

</body>
</html>
