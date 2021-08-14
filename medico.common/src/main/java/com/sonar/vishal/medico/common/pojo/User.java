package com.sonar.vishal.medico.common.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userlogin")
public class User implements Serializable {

	private static final long serialVersionUID = 2819650697965419372L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username", nullable = false, length = 20)
	private String userName;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	@Transient
	private String roleAsString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleAsString() {
		return roleAsString;
	}

	public void setRoleAsString(String roleAsString) {
		this.roleAsString = roleAsString;
	}

	public void update() {
		setRoleAsString(getRole().getName());
	}
}
