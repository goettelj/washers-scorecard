package com.mankind.washers.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.*;

import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="games")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Team homeTeam;
	private Team guestTeam;
	private Lineup homeLineup;
	private Lineup guestLineup;
	private int numberOfPlayers;
	private int objectiveScore;
	private boolean winByTwo;
	private int homeScore;
	private int guestScore;
	private Date time;
	private Team winner;
	private SortedSet<Frame> frames;
	
	public Game() {
		numberOfPlayers = 4;
		time = new Date();
		homeTeam = new Team();
		guestTeam = new Team();
		frames = new TreeSet<Frame>();
	}
	
	@Id @GeneratedValue 
	@Column(name="game_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="home_team_id")
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="guest_team_id")
	public Team getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="home_lineup_id")
	public Lineup getHomeLineup() {
		return homeLineup;
	}
	public void setHomeLineup(Lineup homeLineup) {
		this.homeLineup = homeLineup;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="guest_lineup_id")
	public Lineup getGuestLineup() {
		return guestLineup;
	}
	public void setGuestLineup(Lineup guestLineup) {
		this.guestLineup = guestLineup;
	}

	@Column(name="number_of_players")
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	@Column(name="objective_score")
	public int getObjectiveScore() {
		return objectiveScore;
	}
	public void setObjectiveScore(int objectiveScore) {
		this.objectiveScore = objectiveScore;
	}
	
	@Type(type="numeric_boolean")
	@Column(name="win_by_two")
	public boolean isWinByTwo() {
		return winByTwo;
	}
	public void setWinByTwo(boolean winByTwo) {
		this.winByTwo = winByTwo;
	}
	
	@Column(name="home_score")
	@NumberFormat(pattern="00")
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	
	@Column(name="guest_score")
	@NumberFormat(pattern="00")
	public int getGuestScore() {
		return guestScore;
	}
	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="game_time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@OneToOne
	@JoinColumn(name="winning_team_id")
	public Team getWinner() {
		return winner;
	}
	public void setWinner(Team winner) {
		this.winner = winner;
	}

	@OneToMany(mappedBy="game")
	@SortNatural
	public SortedSet<Frame> getFrames() {
		return frames;
	}
	public void setFrames(SortedSet<Frame> frames) {
		this.frames = frames;
	}

	@Transient
	public Frame getFrame(int frameNumber, Frame.Type frameType) {
		for (Frame frame : this.frames) {
			if (frame.getNumber() == frameNumber && frame.getType() == frameType) {
				return frame;
			}
		}
		throw new NoSuchElementException(String.format("Could not find frame %s of %d for game id %d.", frameType, frameNumber, this.id));
	}
	
	@Transient
	public Frame getLastFrame() {
		try {
			return this.frames.last();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "Game (" + this.id + ")";
	}
	
	
	
	
	
	
	
}
