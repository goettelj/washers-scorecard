package com.mankind.washers.domain;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.*;

@Entity
@Table(name="attempts")
public class Attempt implements Serializable, Comparable<Attempt> {

	private static final long serialVersionUID = 1L;

	private long id;
	private Frame frame;
	private Player player;
	private Team.Type teamType;
	private int number;
	private int playerNumber;
	private Integer points;
	private float positionX;
	private float positionY;
	
//	public class AttemptNumberComparator implements Comparator<Attempt> {
//
//		@Override
//		public int compare(Attempt attempt1, Attempt attempt2) {
//			return Integer.valueOf(attempt1.getNumber())
//		}
//		
//	}
	
	@Id
	@GeneratedValue
	@Column(name="attempt_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="frame_id")
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) { 
		this.frame = frame;
	}
	
	@ManyToOne
	@JoinColumn(name="player_id")
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="team_type")
	public Team.Type getTeamType() {
		return teamType;
	}
	public void setTeamType(Team.Type teamType) {
		this.teamType = teamType;
	}
	
	//THIS WILL BE NUMBERED 1-8 FOR EACH FRAME
	@Column(name="attempt_number")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Column(name="player_attempt_number")
	public int getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	@Column
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	@Column(name="position_x")
	public float getPositionX() {
		return positionX;
	}
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}
	
	@Column(name="position_y")
	public float getPositionY() {
		return positionY;
	}
	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Attempt)obj).getId();
	}
	@Override
	public int compareTo(Attempt o) {
		return Integer.valueOf(this.number).compareTo(Integer.valueOf(o.number));
	}
	@Override
	public String toString() {
		return String.format("Attempt(%d) - Frame: %s AttemptNumber: %d; Team Type: %s; Player: %s", this.id, this.frame, this.number, this.teamType, this.player);
	}
	
	
	
	
	
	
	
	
}
