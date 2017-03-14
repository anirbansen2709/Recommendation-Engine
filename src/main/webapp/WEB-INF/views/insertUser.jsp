<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 19/01/2017
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="./uiFrame.jsp" %>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap-rtl.css"/>--%>
<%--<script type="text/javascript" src="./resources/js/jquery-1.12.0.min.js"></script>--%>
<!-- Main section-->
<style>
  .content-wrapper > h3,/* .content-wrapper > .content-heading*/ {
    margin-bottom: 4px;
    padding: 4px;
  }
  .wrapper > section{
    top: -20px;
  }
</style>
<section>

  <div class="content-wrapper">
    <h3>Device management
      <small>Add new SNMP devices</small>
    </h3>
    <!-- START panel-->

    <div class="col-md-3 panel panel-default" style="border: 4px solid #eee;" id="userForm">
      <h3> Create User</h3>
      <br>

      <form autocomplete="off" role="form" id="createUserForm" >

        <div class="form-group row">
          <label class="col-sm-4 control-label" for="userid_admin">UserName*</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="userid_admin" placeholder="User Name" required="required">
          </div>

          <div id="underInput">

            </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-4 control-label" for="password_admin">Password*</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="password_admin" placeholder="Password" required="required">
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-4 control-label" for="passwordagain_admin">Confirm Password*</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="passwordagain_admin" placeholder="Confirm Password">
          </div>
        </div>

        <div class="form-group row">
          <label class="col-sm-4 control-label" for="username">Full name</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="username">
          </div>
        </div>
        <div class="form-group row">
          <label  class="col-sm-4 control-label" for="email">Email</label>
          <div class="col-sm-8">
            <input type="email" class="form-control" id="email" placeholder="Email">
          </div>
        </div>

        <div class="form-actions row">
          <div class="col-sm-3"></div>
          <div class="col-sm-4">
            <button id="addUserButton" type="submit" class="btn btn-default"><span
                    class='glyphicon glyphicon-user'></span> Create
            </button>
          </div>
          <div class="col-sm-4">
            <button id="resetUserButton" type="reset" class="btn btn-default"><span
                    class='glyphicon glyphicon-remove'></span> Reset
            </button>
          </div>
        </div>
      </form>
      <br>
    </div>

  </div>
</section>

<%--this will import footer and all the commom js--%>
<%@ include file="./uiFrameFooter.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--for glypicon--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--JS file for inserting user--%>
<script src="/resources/app/js/insertUser.js"></script>



</body>




</html>
