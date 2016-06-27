package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Adventure extends AbstractTimestampEntity implements Serializable {
	public enum AdventureStatus {
		OPENED, STARTED, CLOSED, CANCELLED
	}

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "creatorId")
	private User creator;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private AdventureStatus status;

	public Adventure() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public AdventureStatus getStatus() {
		return status;
	}

	public void setStatus(AdventureStatus status) {
		this.status = status;
	}

}
