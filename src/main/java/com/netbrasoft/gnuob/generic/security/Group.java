package com.netbrasoft.gnuob.generic.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = Group.ENTITY)
@Table(name = Group.TABLE)
@XmlRootElement(name = Group.ENTITY)
public class Group extends Access {

	private static final long serialVersionUID = -3688942214897329995L;
	protected static final String ENTITY = "Group";
	protected static final String TABLE = "GNUOB_GROUPS";

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT")
	private String description;

	public Group() {

	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	@XmlElement(name = "name", required = true)
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}
}