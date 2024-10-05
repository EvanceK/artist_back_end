package com.artist.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

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
	private String media;
	private Integer delicated;
	private String dimensions;
	private String period;
	private String status;


	public PaintingDTO() {
		super();
	}


	public PaintingDTO(String paintingId, String paintingName, String artisId, String artisName, String largUrl,
			String smallUrl, Double price, String date, String style, LocalDateTime uploadDate, String genre,
			String media, Integer delicated, String dimensions, String period, String status) {
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
		this.media = media;
		this.delicated = delicated;
		this.dimensions = dimensions;
		this.period = period;
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


	public String getDimensions() {
		return dimensions;
	}


	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}


}
