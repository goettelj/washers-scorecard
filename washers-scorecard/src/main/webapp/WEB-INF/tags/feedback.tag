<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- TAG ATTRIBUTES --%>
<%@ attribute name="message" type="java.lang.String"%>
<%@ attribute name="cssClass" type="java.lang.String"%>
<%@ attribute name="isCloseable" type="java.lang.Boolean"%>

<c:if test="${isCloseable == null}">
	<c:set var="isCloseable" value="${true}" />
</c:if>

<c:if test="${ message != '' }">

	<%-- SET LABEL TEXT --%>
	<c:choose>
		<c:when test="${cssClass == 'alert-success'}">
			<c:set var="label">SUCCESS ::</c:set>
		</c:when>
		<c:when test="${cssClass == 'alert-danger'}">
			<c:set var="label">ERROR ::</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="label">INFO ::</c:set>
		</c:otherwise>
	</c:choose>

	<%-- SET cssClass IF EMPTY --%>
	<c:if test="${empty cssClass}">
		<c:set var="cssClass">alert-info</c:set>
	</c:if>
	<div class="row">
		<div class="col-xs-12 col-sm-offset-4 col-sm-4">

			<div class="alert ${cssClass}">
				<c:if test="${isCloseable}">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">×</a>
				</c:if>
				<c:choose>
					<c:when test="${not empty message}">
						<strong>${label}</strong> ${fn:escapeXml(message)}
				</c:when>
					<c:otherwise>
						<jsp:doBody />
					</c:otherwise>
				</c:choose>

			</div><!-- /.alert -->
		</div><!-- .col -->
	</div><!-- /.row -->

</c:if>


