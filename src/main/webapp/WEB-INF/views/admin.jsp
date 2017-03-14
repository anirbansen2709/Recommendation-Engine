<%--
  ~ Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
  ~ http://www.gammanalytics.com/
  ~ --------------------------------------------------------------------------------------------
  ~ The software in this package is published under the terms of the EUL (End User license)
  ~ agreement a copy of which has been included with this distribution in the license.txt file.
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/22/2016
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/loginAdmin/jquery-ui.css">
<style>
tbody > tr:hover {
cursor: pointer;
}

.fc-header-title h2 {
font-size: 1.4em;
}

.panel {
margin-bottom: 21px;
background-color: #ffffff;
border: 1px solid transparent;
border-radius: 4px !important;
-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
}

.panel-default {
border-color: #cfdbe2;
}

.panel.panel-default {
border-top-width: 3px;
}

.fixedscroll {
max-height: 500px;
width: 100%;
margin: 0;
overflow-y: auto;
}
.wrapper > section {
    margin-bottom: 0px !important;
}
</style>
<!-- Main section-->
<section>
    <!-- Page content-->
    <div class="content-wrapper">
        <div class="content-heading">
            Administrator Management
        </div>
        <!-- START widgets box-->
        <div class="row">
            <div class="col-md-9 panel panel-default">
                <h3> List of User</h3>

                <div class="fixedscroll">
                    <table class="table table-hover context-menu-wtn" id="user_table" class="display">
                        <thead>
                        <tr>
                            <th>UserName</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>

            <div class="col-md-3 panel panel-default" style="border: 4px solid #eee;" id="userForm">
                <h3> Create User</h3>
                <br>

                <form autocomplete="off" role="form" id="createUserForm">

                    <div class="form-group row">
                        <label class="col-sm-4 control-label" for="userid_admin">UserName*</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="userid_admin" placeholder="User Name" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 control-label" for="password_admin">Password*</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="password_admin" placeholder="Password" required="required">
                        </div>
                        <%--<input type="password" class="form-control" id="password_admin" placeholder="Password">--%>
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
                    <div class="form-group row">
                        <label class="col-sm-4 control-label" for="contact">Contact</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="contact" placeholder="contact">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-4 control-label" >Role*</label>
                        <div class="col-sm-8">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="userType" id="admin" value="admin" checked>
                                    Admin
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="userType" id="super" value="super" checked>
                                    Super
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="userType" id="normal" value="user" checked>
                                    Normal
                                </label>
                            </div>

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
                    <%--<button id="addUserButton" type="submit" class="btn btn-default"><span
                            class='glyphicon glyphicon-user'></span> Create
                    </button>
                    <button id="resetUserButton" type="reset" class="btn btn-default"><span
                            class='glyphicon glyphicon-remove'></span> Reset
                    </button>--%>
                </form>
                <br>

            </div>
        </div>
        </div>
    </div>
</section>
</div>
<!-- =============== VENDOR SCRIPTS ===============-->
<!-- MODERNIZR-->
<script src="./resources/vendor/modernizr/modernizr.js"></script>
<!-- JQUERY-->
<script src="./resources/vendor/jquery/dist/jquery.js"></script>
<!-- BOOTSTRAP-->
<script src="./resources/vendor/bootstrap/dist/js/bootstrap.js"></script>
<!-- STORAGE API-->
<script src="./resources/vendor/jQuery-Storage-API/jquery.storageapi.js"></script>

<!-- JQUERY EASING-->
<script src="./resources/vendor/jquery.easing/js/jquery.easing.js"></script>
<!-- ANIMO-->
<script src="./resources/vendor/animo.js/animo.js"></script>
<!-- SLIMSCROLL-->
<script src="./resources/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<!-- SCREENFULL-->
<script src="./resources/vendor/screenfull/dist/screenfull.js"></script>
<!-- LOCALIZE-->
<script src="./resources/vendor/jquery-localize-i18n/dist/jquery.localize.js"></script>
<!-- RTL demo-->
<script src="./resources/app/js/demo/demo-rtl.js"></script>
<!-- =============== PAGE VENDOR SCRIPTS ===============-->
<!-- SPARKLINE-->
<script src="./resources/app/vendor/sparklines/jquery.sparkline.min.js"></script>
<!-- CLASSY LOADER-->
<script src="./resources/vendor/jquery-classyloader/js/jquery.classyloader.min.js"></script>
<!-- MOMENT JS-->
<script src="./resources/vendor/moment/min/moment-with-locales.min.js"></script>
<!-- SKYCONS-->
<script src="./resources/vendor/skycons/skycons.js"></script>
<!-- DEMO-->
<script src="./resources/app/js/demo/demo-flot.js"></script>
<!-- =============== PAGE VENDOR SCRIPTS ===============-->
<script src="./resources/app/js/demo/demo-panels.js"></script>
<!-- =============== APP SCRIPTS ===============-->
<script src="./resources/app/js/app.js"></script>

<!-- DATATABLES-->
<script src="./resources/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/vendor/datatables-colvis/js/dataTables.colVis.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrap.js"></script>
<script src="./resources/vendor/datatable-bootstrap/js/dataTables.bootstrapPagination.js"></script>

</body>


<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                Are you sure?
            </div>
            <div class="modal-body">
                Delete the user? This cannot be undone.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="#" data-dismiss="modal" id="deleteconfirm" class="btn btn-danger danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="reset-user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                This will reset the password
            </div>
            <div class="modal-body">
                <form id="resetform" class="form-signin" role="form" action="./resetPassword" method="POST">
                    <h3 class="form-signin-heading">Enter New Password</h3>
                    <input type="password" id="password_reset" name="password_reset" class="form-control"
                           placeholder="Enter Password" autofocus required>
                    <br>
                    <input type="password" name="passwordagain_reset" id="passwordagain_reset" class="form-control"
                           placeholder="Enter Password Again"/>
                    <br>
                    <button class="btn btn-default btn-primary" type="submit">Reset Password</button>

                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </form>
            </div>
            <div class="modal-footer">

                The user will have to change his password when he logs in next time.
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="passwordmatch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                Password do not match. Please try again.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="emailvalid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                Email is not valid. Please enter a valid email.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="emailrequired" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                You have not provided an email. Continue?
            </div>
            <div class="modal-footer">
                <button type="button" id="emailrequiredok" class="btn btn-default" data-dismiss="modal">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./resources/js/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="./resources/js/bootstrap.min.js"></script>
<script src="./resources/loginAdmin/fullcalendar.min.js"></script>


<script type="text/javascript">
    var selected_id;
    function listUsers() {

        $.ajax({
            url: "./listUser",
            dataType: "json",
            success: function (data) {

                $('#user_table tr:gt(0)').remove();
                jQuery.each(data['payload'], function (index, value) {
                    var stmt = "<tr id='" + value['id'] + "' ><td>" + value['userid'] + "</td><td>" + value['username'] + "</td><td>" + value['role'] +
                            "</td><td>";
                    if (value['userid'] != 'admin') stmt += "<button type='submit' class='deleteUserButton btn btn-sm'><span class='glyphicon glyphicon-trash'></span>  Delete </button> <button type='submit' class='resetUserButton btn btn-sm'><span class='glyphicon glyphicon-refresh'></span>  Reset</button>";
                    stmt += "</td>"

                    $('#user_table').append(stmt + "</tr>");
                });


                $('.deleteUserButton').click(function (e) {
                    e.preventDefault();
                    selected_id = this.parentNode.parentNode.id;

                    $('#confirm-delete').modal('show');

                    event.preventDefault();
                });

                $('.resetUserButton').click(function (e) {
                    e.preventDefault();
                    selected_id = this.parentNode.parentNode.id;
                    $('#reset-user').modal('show');

                    event.preventDefault();
                });
            }, error: function (data) {
            }
        });
    }
    $(document).ready(function () {
        listUsers();

    });

    $('#deleteconfirm').click(function (e) {

        $.ajax({
            url: "deleteUser?userid=" + selected_id,
            success: function (data, status) {
                console.log('Delete user :' + status);
                listUsers();

            }
        });
    });
    function createUser() {

        $.ajax({
            type: 'POST',
            url: "createUser?userid=" + $('#userid_admin').val() + "&username=" + $('#username').val() + "&email=" + $('#email').val() + "&password=" + $('#password_admin').val() + "&role=" + $("input[name='userType']:checked").val(),
            //data: $form.serialize(),
            success: function (data, status) {
                listUsers();
                console.log('Create user :' + status);
            }
        });
    }
    function validateEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    $('#emailrequiredok').click(function (e) {
        createUser();
    });
    $('#addUserButton').click(function (e) {
        e.preventDefault();
        // validate form actions cleaned for clearity.
        var $form = $("searchForm");
        if ($('#password_admin').val() != $('#passwordagain_admin').val()) {
            console.log('Password do not match');
            $('#passwordmatch').modal('show');
            return;
        }

        if ($('#email').val() == '') {
            console.log('email empty');
            $('#emailrequired').modal('show');
            return;
        } else if (!validateEmail($('#email').val())) {
            $('#emailvalid').modal('show');
            return;
        }

        createUser();
        event.preventDefault();
    });
    $("#resetform").submit(function (e) {
        if ($('#password_reset').val() != $('#passwordagain_reset').val()) {
            console.log('Password do not match');
            e.preventDefault();
            $('#passwordmatch').modal('show');

        }
        else {
            // $(this).children('#passwordagain_reset').remove();
            var input = $("<input>")
                    .attr("type", "hidden")
                    .attr("name", "user_id").val(selected_id);
            $('#resetform').append($(input));
        }

    });

</script>

</html>