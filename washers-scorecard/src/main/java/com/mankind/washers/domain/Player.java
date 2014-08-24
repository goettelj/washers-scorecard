package com.mankind.washers.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="players")
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private User user;
	private String guestName;
	
	@Id @GeneratedValue
	@Column(name="player_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="player_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="guest_name")
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	@Transient
	public String getName() {
		
		String name = "";
		if ( this.user != null ) {
			name += " " + this.user.getNickname();
		} else {
			name += " " + this.guestName;
		}
		
		return name;
	}
	
	@Override
	public String toString() {
		String playerString = "PLAYER " + getName() + "(ID: " + this.id + ")";
		
		return playerString;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
