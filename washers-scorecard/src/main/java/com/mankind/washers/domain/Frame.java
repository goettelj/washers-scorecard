package com.mankind.washers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="frames")
public class Frame implements Serializable, Comparable<Frame> {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(Frame.class);
	
	public enum Type {
		TOP(1, "Top"), BOTTOM(2, "Bottom");
		
		private int value;
		private String label;
		
		private Type(int value, String label) {
			this.value = value;
			this.label = label;
		}
		
		public String getLabel() {
			return this.label;
		}
		
	}
	
	private long id;
	private Game game;
	private Type type;
	private int number;
	private Player homePlayer;
	private Player guestPlayer;
	private Team.Type firstTeam;
	private int homePoints;
	private int guestPoints;
	private List<Attempt> attempts;
	
	public Frame() {}
	
	public Frame(Game game, int number, Type type, Team.Type firstTeam) {
		this.game = game;
		this.number = number;
		this.type = type;
		this.firstTeam = firstTeam;
		this.attempts = new ArrayList<Attempt>();
	}
	
	@Id @GeneratedValue
	@Column(name="frame_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="game_id")
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="frame_type")
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@Column(name="frame_number")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@ManyToOne
	@JoinColumn(name="home_player_id")
	public Player getHomePlayer() {
		return homePlayer;
	}
	public void setHomePlayer(Player homePlayer) {
		this.homePlayer = homePlayer;
	}
	
	@ManyToOne
	@JoinColumn(name="guest_player_id")
	public Player getGuestPlayer() {
		return guestPlayer;
	}
	public void setGuestPlayer(Player guestPlayer) {
		this.guestPlayer = guestPlayer;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="first_team")
	public Team.Type getFirstTeam() {
		return firstTeam;
	}
	public void setFirstTeam(Team.Type firstTeam) {
		this.firstTeam = firstTeam;
	}
	
	@Column(name="home_points")
	public int getHomePoints() {
		return homePoints;
	}
	public void setHomePoints(int homePoints) {
		this.homePoints = homePoints;
	}

	@Column(name="guest_points")
	public int getGuestPoints() {
		return guestPoints;
	}

	public void setGuestPoints(int guestPoints) {
		this.guestPoints = guestPoints;
	}

	@OneToMany(mappedBy="frame")
	public List<Attempt> getAttempts() {
		return attempts;
	}
	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}
	
	@Transient
	public List<Attempt> getAttemptsForPlayer(Player player){
		List<Attempt> attempts = new ArrayList<Attempt>();
		
		for (Attempt attempt: this.getAttempts()) {
			if (attempt.getPlayer().equals(player)) {
				attempts.add(attempt);
			}
		}
		
		return attempts;
		
	}
	
	@Transient
	public Player getFirstPlayer() {
		if (this.firstTeam == Team.Type.HOME) {
			return this.getHomePlayer();
		} else {
			return this.getGuestPlayer();
		}
	}
	
	@Transient
	public Player getSecondPlayer() {
		if (this.firstTeam == Team.Type.HOME) {
			return this.getGuestPlayer();
		} else {
			return this.getHomePlayer();
		}
	}
	
	@Transient
	public Team.Type getSecondTeam(){
		if (this.firstTeam == Team.Type.HOME) {
			return Team.Type.GUEST;
		} else {
			return Team.Type.HOME;
		}
	}
	
	@Override
	public int compareTo(Frame o) {
		if ( this.number > o.number) {
			return 1;
		} else if ( this.number < o.number) {
			return -1;
		} else {
			assert( this.type != null && o.type != null);
			return Integer.valueOf(this.type.value).compareTo(Integer.valueOf(o.type.value));
		}
	}

	@Override
	public String toString() {
		return "FRAME(" + this.id + ") " + this.type + " of " + this.number;
	}
	
	
	
	
}
