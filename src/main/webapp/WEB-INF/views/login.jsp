<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>N View | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="./resources/login/css/style.css" rel='stylesheet' type='text/css'/>
    <!-- FONT AWESOME-->
    <link rel="stylesheet" href="./resources/vendor/fontawesome/css/font-awesome.min.css">
    <!-- =============== BOOTSTRAP STYLES ===============-->
    <link rel="stylesheet" href="./resources/vendor/bootstrap/dist/css/bootstrap.css" id="bscss">

    <link rel="icon" href="./resources/images/snmp.jpg" type="image/x-icon">


    <script src="./resources/login/js/jquery.min.js"></script>
    <script src="./resources/login/js/easyResponsiveTabs.js" type="text/javascript"></script>
    <%--<script src="http://code.jquery.com/jquery-1.10.2.js"></script>--%>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
            $(function() {
                $( ".box" ).draggable();
            });
        });
    </script>
<style>
    body {
        color: #9E9FA0;
    }
</style>
</head>
<body>
<div class="main-content box blurred-bg tinted">
    <div class="sap_tabs ">

        <div id="horizontalTab" style="display: block; width: 80%; margin: 0px;" class="blurIt">

            <ul>
                <li class="resp-tab-item"><span>musicRecommendation</span></li>

            </ul>
            <!---->

            <div class="tab-2 resp-tab-content">
                <div class="facts">
                    <div class="register">
                        <form:form class="form-1" method="POST" action="login" commandName="loginDetails">
                            <p class="field">
                                <input type="text" name="userId" id="userId" placeholder="User ID" required="">
                                <span class="fa fa-user"></span>

                            </p>
                            <p class="field">
                                <%--<input type="password" name="passwd" id="passwd"  placeholder="PASSWORD">--%>
                                <input type="password" name="passwd" id="passwd"  placeholder="Password" required="" autocomplete="off">
                                <span class="fa fa-lock"></span>
                            </p>
                            <p class="field" style="width: 55%;">
                            <input type="checkbox" value="" name="remember">
                                <span font: 26px Serif; margin: 0 0 40px 0; color: ghostwhite;>Remember Me</span>
                            </p>
                            <p class="submit">
                            <div class="sign-in">
                                <input type="submit" value="Sign in"/>
                            </div>
                            </p>
                        </form:form>
                        <c:if test="${not empty errorMessage}">
                            <span style="color:#dd0000;font-size:0.8em;">   ${errorMessage}</span>
                        </c:if>

                    </div>
                </div>
            </div>
            <br>
            <br>
            <!--start-copyright-->
            <div class="copy-right">
                <div class="wrap">
                    <p style="font-size: small;opacity: 0.36;">&copy; 2016 musicRecommendation. All Rights Reserved </p><p style="font-size: small;opacity: 0.3;"> Design by Rushil and Rishav</p>
                </div>
            </div>
            <!--//end-copyright-->

        </div>

    </div>
</div>
</body>
</html>