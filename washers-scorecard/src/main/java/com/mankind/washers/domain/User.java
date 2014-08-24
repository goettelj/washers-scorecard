package com.mankind.washers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	private long id;
	private String ident;
	private String firstName;
	private String lastName;
	private String nickname;
	private String email;
	private String photo;
	private String password;
	private Date passwordExpirationDate;
	private String pin;
	private Date pinExpirationDate;
	private Player player;
	
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="nickname")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="password_expiration_date")
	public Date getPasswordExpirationDate() {
		return passwordExpirationDate;
	}
	public void setPasswordExpirationDate(Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
	}
	
	@Column
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pin_expiration_date")
	public Date getPinExpirationDate() {
		return pinExpirationDate;
	}
	public void setPinExpirationDate(Date pinExpirationDate) {
		this.pinExpirationDate = pinExpirationDate;
	}
	
	@OneToOne(mappedBy="user")
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Transient
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	
	
	@Override
	public String toString() {
		return getFullName();
	}
	
	/**
	 * IMPLEMENTING UserDetails INTERFACE
	 */
	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}
	
	@Override
	@Transient
	public String getUsername() {
		return this.ident;
	}
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}
	
	
}
