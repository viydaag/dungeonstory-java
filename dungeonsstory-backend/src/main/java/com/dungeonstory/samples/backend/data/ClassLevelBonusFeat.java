package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ClassLevelBonusFeatId.class)
@Table(name = "ClassLevelBonusFeat")
public class ClassLevelBonusFeat extends AbstractTimestampEntity implements	Serializable {

	private static final long serialVersionUID = 5198775399118036404L;

	@Id
	@Column(name = "classId")
	private Long classId;

	@Id
	@Column(name = "levelId")
	private Long levelId;

	@Id
	@Column(name = "featId")
	private Long featId;

	@ManyToOne
	@JoinColumn(name = "classId", updatable = false, insertable = false)
	private Class classe;

	@ManyToOne
	@JoinColumn(name = "levelId", updatable = false, insertable = false)
	private Level level;

	@ManyToOne
	@JoinColumn(name = "featId", updatable = false, insertable = false)
	private Feat feat;

	public ClassLevelBonusFeat() {
		// TODO Auto-generated constructor stub
	}

}
