<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="washers" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<h1>Set Lineups</h1>
	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>	
						<th>&nbsp;</th>
						<th>Home</th>
						<th>Guest</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" begin="0" end="${ (game.numberOfPlayers / 2) - 1}">
						<tr>
							<td>${i + 1}</td>
							<td>${homeLineupPlayers[i].player.name}</td>
							<td>${guestLineupPlayers[i].player.name}</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-offset-5 col-sm-2">
			<spring:url var="frameUrl" value="/games/{gameId}/frames/1/top">
				<spring:param name="gameId" value="${game.id}" />
			</spring:url>
			<a href="${frameUrl}" class="btn btn-red btn-lg btn-100">Start Game</a>
		</div>
	</div>
	