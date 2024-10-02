package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artis")
public class Artis {
	@Id
	@Column(name="artis_id")
	private String artisId;
	@Column(name="artis_name")

	private String artisName;

	public Artis() {
	}

	public Artis(String artisId, String artisName) {
		this.artisId = artisId;
		this.artisName = artisName;
	}

	public String getArtisId() {
		return artisId;
	}

	public void setArtisId(String artisId) {
		this.artisId = artisId;
	}

	public String getArtisName() {
		return artisName;
	}

	public void setArtisName(String artisName) {
		this.artisName = artisName;
	}
}
