package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Adventure extends AbstractTimestampEntity implements Serializable {
	
	private static final long serialVersionUID = 2976001308437054432L;

	public enum AdventureStatus {
		OPENED, STARTED, CLOSED, CANCELLED
	}

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "creatorId")
	private User creator;

	@NotNull
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private AdventureStatus status;
	
	@OneToMany(mappedBy="adventure")
	private List<Message> messages;

	public Adventure() {
		super();
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
