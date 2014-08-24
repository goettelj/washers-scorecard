<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="washers" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>New Game</h2>
<form:form modelAttribute="game" action="/washers/games" method="POST">

	<div class="form-group">
		<label class="control-label">Number of Players</label>
		<div class="text-center">
			<div class="btn-group btn-group-lg">
				<button class="btn btn-large btn-number-of-players btn-toggle">2</button>
				<button class="btn btn-large btn-number-of-players btn-toggle on">4</button>
			</div>
			<form:radiobutton class="hidden" path="numberOfPlayers" value="2" id="numberOfPlayers2" />
			<form:radiobutton class="hidden" path="numberOfPlayers" value="4" id="numberOfPlayers4" />
		</div>
	</div>
	
	<fieldset>
		<legend>Home Team</legend>
		<div class="form-group">
			<label class="control-label">Player 1</label>
			<form:input class="form-control" path="homeTeam.players[0].guestName" />
		</div>
		<div class="form-group player-2">
			<label class="control-label">Player 2</label>
			<form:input class="form-control" path="homeTeam.players[1].guestName" />
		</div>
	</fieldset>
	
	<fieldset>
		<legend>Guest Team</legend>
		<div class="form-group">
			<label class="control-label">Player 1</label>
			<form:input class="form-control" path="guestTeam.players[0].guestName" />
		</div>
		<div class="form-group player-2">
			<label class="control-label">Player 2</label>
			<form:input class="form-control" path="guestTeam.players[1].guestName" />
		</div>
	</fieldset>

	<div class="form-group">
		<div class="text-center">
			<button class="btn btn-lg btn-red" type="submit">Start Game</button>
		</div>
	</div>

</form:form>