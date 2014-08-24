package com.mankind.washers.domain;
import java.io.Serializable;

import com.mankind.washers.domain.Attempt;
import com.mankind.washers.domain.Frame;
import com.mankind.washers.domain.Player;
import com.mankind.washers.domain.Team;


public class AttemptJson implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private long frameId;
	private long playerId;
	private Team.Type teamType;
	private int frameAttemptNumber;
	private int playerAttemptNumber;
	private Integer points;
	private float positionX;
	private float positionY;
	
	public AttemptJson(){}
	
	public AttemptJson(Attempt attempt) {
		this.id = attempt.getId();
		this.frameId = attempt.getFrame().getId();
		this.playerId = attempt.getPlayer().getId();
		this.teamType = attempt.getTeamType();
		this.frameAttemptNumber = attempt.getNumber();
		this.playerAttemptNumber = attempt.getPlayerNumber();
		this.points = attempt.getPoints();
		this.positionX = attempt.getPositionX();
		this.positionY = attempt.getPositionY();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFrameId() {
		return frameId;
	}

	public void setFrameId(long frameId) {
		this.frameId = frameId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public Team.Type getTeamType() {
		return teamType;
	}

	public void setTeamType(Team.Type teamType) {
		this.teamType = teamType;
	}

	public int getFrameAttemptNumber() {
		return frameAttemptNumber;
	}

	public void setFrameAttemptNumber(int frameAttemptNumber) {
		this.frameAttemptNumber = frameAttemptNumber;
	}

	public int getPlayerAttemptNumber() {
		return playerAttemptNumber;
	}

	public void setPlayerAttemptNumber(int playerAttemptNumber) {
		this.playerAttemptNumber = playerAttemptNumber;
	}

	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	
}
