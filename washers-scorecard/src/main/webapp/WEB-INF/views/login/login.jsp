<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="washers" tagdir="/WEB-INF/tags"%>

<washers:feedback message="${errorMessage}" cssClass="alert-danger" />
<washers:feedback message="${successMessage}" cssClass="alert-success" />

<div class="row">
	<div class="col-xs-12 col-sm-offset-4 col-sm-4">
		<washers:portlet portletClass="portlet-orange" title="Login">
			<form class="form" action="/washers/login" method="post">
				<div class="form-group">
					<label class="control-label">Email</label> <input type="text"
						class="form-control" name="email" />
				</div>
				<div class="form-group">
					<label class="control-label">Password</label> <input
						type="password" class="form-control" name="password" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-orange">Login</button>
				</div>
				<div class="form-group">
					<a class="reset-password-link" href="/washers/users/reset-password">Forgot Password?</a>
				</div>
			</form>
			
		</washers:portlet>
	</div>
</div>