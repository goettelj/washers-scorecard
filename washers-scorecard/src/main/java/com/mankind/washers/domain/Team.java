package com.mankind.washers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="teams")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Type {
		HOME("Home"), GUEST("Guest");
		
		private String label;
		
		private Type(String label) {
			this.label = label;
		}
	}
	
	private long id;
	private String name;
	private List<Player> players;
	
	public Team() {
		
		players = new ArrayList<Player>();
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="team_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="team_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="team_player_map",
			joinColumns= { @JoinColumn(name="team_id") }, 
			inverseJoinColumns= { @JoinColumn(name="player_id") } )
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
