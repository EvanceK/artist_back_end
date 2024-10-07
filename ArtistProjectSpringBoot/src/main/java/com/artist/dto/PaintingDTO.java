package com.artist.dto;

import java.time.LocalDateTime;

public class PaintingDTO {
	private String paintingId;
	private String paintingName;
	private String artisId;
	private String artisName;
	private String largUrl;
	private String smallUrl;
	private Double price;
	private String date;
	private String style;
	private LocalDateTime uploadDate;
	private String genre;
	private Integer delicated;
	private String status;
	public PaintingDTO() {
		super();
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
	public String getArtisName() {
		return artisName;
	}
	public void setArtisName(String artisName) {
		this.artisName = artisName;
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
	public PaintingDTO(String paintingId, String paintingName, String artisId, String artisName, String largUrl,
			String smallUrl, Double price, String date, String style, LocalDateTime uploadDate, String genre,
			Integer delicated, String status) {
		super();
		this.paintingId = paintingId;
		this.paintingName = paintingName;
		this.artisId = artisId;
		this.artisName = artisName;
		this.largUrl = largUrl;
		this.smallUrl = smallUrl;
		this.price = price;
		this.date = date;
		this.style = style;
		this.uploadDate = uploadDate;
		this.genre = genre;
		this.delicated = delicated;
		this.status = status;
	}

	
}
