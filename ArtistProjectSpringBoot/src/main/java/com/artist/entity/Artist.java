package com.artist.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artis")
public class Artist {
	@Id
	@Column(name="artis_id")
	private String artisId;
	
	@Column(name="artis_name")
	private String artisName;
	
	@Column(name="desciption")
	private String desciption;
	
	@Column(name="url")
	private String url;
	
	
	// 與 Paintings 的關聯
    @OneToMany(mappedBy = "artist")
    private List<Paintings> paintings;
	

	public Artist() {
	}

	public Artist(String artisId, String artisName, String desciption, String url) {
		super();
		this.artisId = artisId;
		this.artisName = artisName;
		this.desciption = desciption;
		this.url = url;
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Paintings> getPaintings() {
		return paintings;
	}

	public void setPaintings(List<Paintings> paintings) {
		this.paintings = paintings;
	}
	
	
}
