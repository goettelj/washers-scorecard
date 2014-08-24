<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!-- ATTRIBTES -->
<%@ attribute name="portletClass" type="java.lang.String" %>
<%@ attribute name="title" type="java.lang.String" %>
<%@ attribute name="titleIconClass" type="java.lang.String" %>


	<div class="portlet ${portletClass}">
		<div class="portlet-heading">
			<div class="portlet-title">
				<h4><i class="fa ${titleIconClass}"></i>${title}</h4>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="portlet-body">
			<jsp:doBody />
		</div>
	</div>