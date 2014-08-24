package com.mankind.washers.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="lineup_player_map")
public class LineupPlayer implements Serializable, Comparable<LineupPlayer> {

	private static final long serialVersionUID = 1L;

	private long id;
	private Lineup lineup;
	private Player player;
	private int number;
	
	public LineupPlayer(){
		this.player = new Player();
	}
	
	public LineupPlayer(Player player) {
		this.player = player;
	}
	
	public LineupPlayer(Player player, Lineup lineup) {
		this.player = player;
		this.lineup = lineup;
	}
	
	public LineupPlayer(Player player, int number) {
		this.player = player;
		this.number = number;
	}
	
	@Id @GeneratedValue
	@Column(name="lineup_player_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="lineup_id")
	public Lineup getLineup() {
		return lineup;
	}
	public void setLineup(Lineup lineup) {
		this.lineup = lineup;
	}
	
	@ManyToOne
	@JoinColumn(name="player_id")
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Column
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public int compareTo(LineupPlayer o) {
		return Integer.valueOf(this.number).compareTo(Integer.valueOf(o.number));
	}

	@Override
	public String toString() {
		String team = this.lineup != null & this.lineup.getTeam() != null ? "TEAM: " + this.lineup.getTeam() : "";
		String game = this.lineup != null & this.lineup.getGame() != null ? " GAME: " + this.lineup.getGame() : "";
		return team + game + " #" + this.number + " - " + this.player;
	}
	
	
}
