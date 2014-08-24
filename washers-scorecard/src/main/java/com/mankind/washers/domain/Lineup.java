package com.mankind.washers.domain;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.*;

import org.hibernate.annotations.SortNatural;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="lineups")
public class Lineup implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(Lineup.class);
	
	private long id;
	private Game game;
	private Team team;
	private SortedSet<LineupPlayer> players;
	
	public Lineup() {
		this.players = new TreeSet<LineupPlayer>();
	}
	
	public Lineup(Game game, Team team) {
		this();
		this.game = game;
		this.team = team;
		int i = 1;
		for (Player player : team.getPlayers()) {
			LineupPlayer lineupPlayer = new LineupPlayer(player, this);
			lineupPlayer.setNumber(i);
			this.players.add(lineupPlayer);
			i++;
		}
	}
	
	@Id @GeneratedValue
	@Column(name="lineup_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="game_id")
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	@ManyToOne
	@JoinColumn(name="team_id")
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@OneToMany(mappedBy="lineup", cascade=CascadeType.ALL)
	@SortNatural
	public SortedSet<LineupPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(SortedSet<LineupPlayer> players) {
		this.players = players;
	}
	
	/**
	 * getPlayerNumber(Player player) 
	 * @param player
	 * @return int
	 */
	@Transient
	public int getPlayerNumber(Player player) {
		for (LineupPlayer lineupPlayer : this.players) {
			logger.debug("Comparing player: " + lineupPlayer.getPlayer() + " to " + player);
			if (lineupPlayer.getPlayer().equals(player)) {
				return lineupPlayer.getNumber();
			}
		}
		throw new IllegalArgumentException(String.format("Player {} does not exist in lineup.", player));
	}
	
	/**
	 * get(int number)
	 * @param number
	 * @return Player
	 */
	public Player get(int number) {
		for (LineupPlayer lineupPlayer : this.players) {
			if (lineupPlayer.getNumber() == number) {
				return lineupPlayer.getPlayer();
			}
		}
		throw new IllegalArgumentException(String.format("This lineup does not have a players in the #%d slot.", number));
	}
	
	/**
	 * getNextPlayer(Player player)
	 * @param player
	 * @return Player
	 */
	@Transient
	public Player getNextPlayer(Player player) {
		int number = getPlayerNumber(player);
		int size = this.players.size();
		if (number >= size) {
			return get(1);
		}
		return get(number + 1);
	}
	
	@Transient
	public Player getFirstPlayer() {
		assert(this.players != null);
		assert(this.players.first() != null);
		return this.getPlayers().first().getPlayer();
	}
	
	@Transient 
	public void add(LineupPlayer player) {
		this.players.add(player);
		player.setLineup(this);
	}
	
	
}
