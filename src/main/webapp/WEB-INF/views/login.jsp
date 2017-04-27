<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 14-Apr-17
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="/resources/css/demo.css" rel='stylesheet' type='text/css'/>
    <script src="./resources/vendor/jquery/dist/jquery.js"></script>
    <script src="/resources/app/js/demo.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
</head>

<body>
<center>
    <div id="form">
        <div class="header-content">
            <div class="header-content-inner">
                <div id="homeHeading">Recommendation Engine</div>
                <hr>
                <p>Want Recommendations for movies? We are here to help</p>
                <button id="button" class="btn btn-primary btn-xl page-scroll">Login/ Sign Up</button>
            </div>
        </div>
        <div class="container">
            <div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-md-8 col-md-offset-2">
                <div id="userform">
                    <ul class="nav nav-tabs nav-justified" role="tablist">
                        <li class="active"><a href="#signup"  role="tab" data-toggle="tab">Sign up</a></li>
                        <li><a href="#login"  role="tab" data-toggle="tab">Log in</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="signup">
                            <h2 class="text-uppercase text-center"> Sign Up for Free</h2>
                            <form id="signup">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="form-group">
                                            <label>First Name<span class="req">*</span> </label>
                                            <input type="text" class="form-control" id="first_name" required data-validation-required-message="Please enter your name." autocomplete="off">
                                            <p class="help-block text-danger"></p>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="form-group">
                                            <label> Last Name<span class="req">*</span> </label>
                                            <input type="text" class="form-control" id="last_name" required data-validation-required-message="Please enter your name." autocomplete="off">
                                            <p class="help-block text-danger"></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label> Your Email<span class="req">*</span> </label>
                                    <input type="email" class="form-control" id="email" required data-validation-required-message="Please enter your email address." autocomplete="off">
                                    <p class="help-block text-danger"></p>
                                </div>

                                <div class="form-group">
                                    <label> Password<span class="req">*</span> </label>
                                    <input type="password" class="form-control" id="password" required data-validation-required-message="Please enter your password" autocomplete="off">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="mrgn-30-top">
                                    <button type="submit" class="btn btn-larger btn-block" id="userSignup"/>
                                    Sign up
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane fade in" id="login">
                            <h2 class="text-uppercase text-center"> Log in</h2>
                            <form id="login2">
                                <div class="form-group">
                                    <label> Your Email<span class="req">*</span> </label>
                                    <input type="email" class="form-control" id="email1" required data-validation-required-message="Please enter your email address." autocomplete="off">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <label> Password<span class="req">*</span> </label>
                                    <input type="password" class="form-control" id="password1" required data-validation-required-message="Please enter your password" autocomplete="off">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="mrgn-30-top">
                                    <button type="submit" class="btn btn-larger btn-block" id="userLogin"/>
                                    Log in
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
    </div>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script src="js/index.js"></script>

    <script src="/resources/app/js/demo.js"></script>

</center>
</body>


</html>
