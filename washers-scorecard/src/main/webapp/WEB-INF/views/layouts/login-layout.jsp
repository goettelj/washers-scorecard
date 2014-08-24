<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		
    	<link href="/washers/resources/theme/css/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    	<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700,300italic,400italic,500italic,700italic' rel="stylesheet" type="text/css">
    	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel="stylesheet" type="text/css">
    	<link href="/washers/resources/theme/icons/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="/washers/resources/theme/css/style.css" rel="stylesheet">
	    <link href="/washers/resources/theme/css/plugins.css" rel="stylesheet">
		
		<!-- STYLES -->
		<link rel="stylesheet" type="text/css" href="/washers/resources/css/theme.css" />
		<link rel="stylesheet" type="text/css" href="/washers/resources/css/common.css" />
		
		
	</head>
	<body>
		<header class="main-header">
			<div class="container">
				<h1>What's Up Now?</h1>
			</div>
		</header>
		<div class="container main-container">
			<tiles:insertAttribute name="content" ignore="true" />
		</div>
		
		<!-- GLOBAL SCRIPTS -->
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	    <script src="/washers/resources/theme/js/plugins/bootstrap/bootstrap.min.js"></script>
	    <script src="/washers/resources/theme/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	    <script src="/washers/resources/theme/js/plugins/popupoverlay/jquery.popupoverlay.js"></script>
	    <script src="/washers/resources/theme/js/plugins/popupoverlay/defaults.js"></script>
	    
	    <script src="/washers/resources/js/login/login.js"></script>
	    
	    <!-- Logout Notification Box -->
	    <!-- 
	    <div id="logout">
	        <div class="logout-message">
	            <img class="img-circle img-logout" src="img/profile-pic.jpg" alt="">
	            <h3>
	                <i class="fa fa-sign-out text-green"></i> Ready to go?
	            </h3>
	            <p>Select "Logout" below if you are ready<br> to end your current session.</p>
	            <ul class="list-inline">
	                <li>
	                    <a href="login.html" class="btn btn-green">
	                        <strong>Logout</strong>
	                    </a>
	                </li>
	                <li>
	                    <button class="logout_close btn btn-green">Cancel</button>
	                </li>
	            </ul>
	        </div>
	    </div>
	     -->
	    <!-- /#logout -->
	    <!-- Logout Notification jQuery -->
	    <!-- 
	    <script src="js/plugins/popupoverlay/logout.js"></script>
	     -->
	    <!-- HISRC Retina Images -->
	    <!-- 
	    <script src="js/plugins/hisrc/hisrc.js"></script>
		-->
	    <!-- THEME SCRIPTS -->
	    <!--
	    <script src="js/flex.js"></script>
		-->
	</body>
</html>