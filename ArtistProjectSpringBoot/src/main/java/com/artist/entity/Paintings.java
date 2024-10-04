package com.artist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paintings")
public class Paintings {
	
	//與artist關聯
    @ManyToOne
    @JoinColumn(name = "artis_id", insertable = false, updatable = false)
    private Artist artist;
	
	@Id
	@Column(name = "painting_id")
	private String paintingId;
	
	@Column(name = "painting_name")
	private String paintingName;
	
	@Column(name = "artis_id")
	private String artisId;
	
	@Column(name = "larg_url")
	private String largUrl;
	
	@Column(name = "small_url")
	private String smallUrl;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "`date`")
	private String date;
	
	@Column(name = "style")
	private String style;
	
	@Column(name = "`upload_date`")
	private LocalDateTime uploadDate;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "media")
	private String media;
	
	@Column(name = "delicated")
	private Integer delicated;
	
	@Column(name = "status")
	private String status;

	public Paintings() {
	}



	public Paintings(String paintingId, String paintingName, String artisId, String largUrl, String smallUrl,
			Double price, String date, String style, LocalDateTime uploadDate, String genre,
			String media, Integer delicated, String status) {
		super();
		this.paintingId = paintingId;
		this.paintingName = paintingName;
		this.artisId = artisId;
		this.largUrl = largUrl;
		this.smallUrl = smallUrl;
		this.price = price;
		this.date = date;
		this.style = style;
		this.uploadDate = uploadDate;
		this.genre = genre;
		this.media = media;
		this.delicated = delicated;
		this.status = status;
	}



	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public String getPaintingName() {
		return paintingName;
	}

	public void setPaintingName(String paintingName) {
		this.paintingName = paintingName;
	}

	public String getArtisId() {
		return artisId;
	}

	public void setArtisId(String artisId) {
		this.artisId = artisId;
	}

	public String getLargUrl() {
		return largUrl;
	}

	public void setLargUrl(String largUrl) {
		this.largUrl = largUrl;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}



	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}


	public Integer getDelicated() {
		return delicated;
	}

	public void setDelicated(Integer delicated) {
		this.delicated = delicated;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	

	public Artist getArtist() {
		return artist;
	}



	public void setArtist(Artist artist) {
		this.artist = artist;
	}



	@Override
	public String toString() {
		return "Paintings [paintingId=" + paintingId + ", paintingName=" + paintingName + ", artisId=" + artisId
				+ ", largUrl=" + largUrl + ", smallUrl=" + smallUrl + ", price=" + price + ", date=" + date + ", style="
				+ style + ", uploadDate=" + uploadDate + ", genre=" + genre + ", media=" + media + ", delicated="
				+ delicated + ", status=" + status + "]";
	}

}
