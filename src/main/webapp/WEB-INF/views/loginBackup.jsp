<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%--
  ~ Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
  ~ http://www.gammanalytics.com/
  ~ --------------------------------------------------------------------------------------------
  ~ The software in this package is published under the terms of the EUL (End User license)
  ~ agreement a copy of which has been included with this distribution in the license.txt file.
  --%>

<html>
<head>
	<title>N View | Login</title>
	<meta name="viewport" content="width = 960, minimum-scale = 0.25, maximum-scale = 1.60">
	<link rel="stylesheet" href="resources/vemdor/jquery-ui/themes/smoothness/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="./resources/css/styleup.css" />
    <link rel="icon" href="resources/images/favicon.ico" type="image/x-icon">
	<style type="text/css">
	body {font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", 
	  Helvetica, Arial, "Lucida Grande", sans-serif;
	 }
	img.content {
	    display: block;
	    margin-left: auto;
	    margin-right: auto;
	}
	.smaller { font-size:0.8em}
	.colored {color:#dc732a}
	.content {color:#00446a; font-size:1.2em; line-height:1.3em;text-align:center;margin: 10px 20px 20px 20px ; }
	<!--
	body { margin:0px; background-color:#fff; height:100% }
	html { height:100% }
	form { margin:0px }
	body > form { height:100% }
	img { margin:0px; border-style:none ;}
	button { margin:0px; border-style:none; padding:0px; background-color:transparent; vertical-align:top }
	table { empty-cells:hide }
	td { padding:0px }
	.f-sp { font-size:1px; visibility:hidden }
	.f-lp { margin-bottom:0px }
	.f-fp { margin-top:0px }
	em { font-style:italic }
	h1 { font-size:18px }
	h1:first-child { margin-top:0px }
	strong { font-weight:bold }
	.style3 { color:#00446a; text-align:center ;line-height:1.6em;}
	.style10 { color:#00446a; text-decoration:underline; text-align:center }
	.style7 { color:#e4701e }
	.style19 { color:#fdb813; text-align:center }
	.style18 { color:#e4701e }
	#PageDiv { position:relative; max-width:960px; min-height:100%; margin:auto }
	-->
	</style>
</head>
<body style="background:#fff">
<div id="PageDiv">
	<div style="float:left;width:33%; text-align:center;font-size:0.8em;">
		<img  class="content" src="./resources/images/logo-claro.png" width="60" height="60" alt="ClaroLogo" style="float: left;">
	</div>
	<!-- <div style="float:left;width:33%; text-align:right;font-size:0.8em;">
		<img  class="content" src="./resources/images/dataportal.png" width="100" height="50" alt="DataPortal Logo" >
	</div> -->
	<div style="float:right;width:33%; margin-right:20px; text-align:right;font-size:0.8em;">
		<img  class="content" src="./resources/images/atslogo.gif" width="157" height="70" alt="ATSLogo" style="float: right;">
	</div>
	<br>
	<div style="float:left;width:98%; display: table-cell;vertical-align: middle;height:350px; text-align:center;font-size:0.8em; background-image:url(./resources/images/dataAnalytics.jpg) ">
         <div class="content" style="float:left;padding:5px">
         			
         				<c:if test="${not empty successMessage}">
						<span style="color:#006600;font-size:0.8em;">   ${successMessage}</span>
					</c:if>
	         		<section class="main" style="padding-left: 50px;">
					<form:form class="form-1" method="POST" action="login" commandName="loginDetails">
						<p class="field">
							<input type="text" name="userId" id="userId" placeholder="USER NAME">
							<i class="icon-user icon-large"></i>
						</p>
							<p class="field">
								<input type="password" name="passwd" id="passwd"  placeholder="PASSWORD">
								<i class="icon-lock icon-large"></i>
						</p>
						<p class="submit">
							<button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
						</p>
					</form:form>
					<c:if test="${not empty errorMessage}">
						<span style="color:#dd0000;font-size:0.8em;">   ${errorMessage}</span>
					</c:if>
					<div>
						
						<span style="color:#dd0000;font-size:0.8em;"> <a href="forgotPassword">Forgot Password?</a></span>
					</div>
				</section>
				
         </div>
 	</div>

</div>
<footer  style="position: fixed;bottom: 0;left: 0;right: 0;height: 25px; text-align: center;color:#00446a; font-size:0.8em;">
	<!-- jsp:include page="layouts/footer.jsp" /-->
</footer>


</body>
</html>
