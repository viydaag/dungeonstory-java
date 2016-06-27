package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class AccessRole extends AbstractTimestampEntity implements Serializable {

	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "role")
	private Set<User> users;

	public AccessRole() {

	}

	public AccessRole(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
