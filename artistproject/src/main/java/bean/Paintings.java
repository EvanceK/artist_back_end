package bean;

import java.time.LocalDateTime;

public class Paintings {

	private String paintingId;
	private String paintingName;
	private String artisId;
	private String largUrl;
	private String smallUrl;
	private Double price;
	private String date;
	private String style;
	private LocalDateTime uploadDate;
	private String period;
	private String genre;
	private String media;
	private String dimensions;
	private Integer delicated;

	public Paintings() {
	}

	public Paintings(String paintingId, String paintingName, String artisId, String largUrl, String smallUrl,
			Double price, String date, String style, LocalDateTime uploadDate, String period, String genre,
			String media, String dimensions, Integer delicated) {
		this.paintingId = paintingId;
		this.paintingName = paintingName;
		this.artisId = artisId;
		this.largUrl = largUrl;
		this.smallUrl = smallUrl;
		this.price = price;
		this.date = date;
		this.style = style;
		this.uploadDate = uploadDate;
		this.period = period;
		this.genre = genre;
		this.media = media;
		this.dimensions = dimensions;
		this.delicated = delicated;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Integer getDelicated() {
		return delicated;
	}

	public void setDelicated(Integer delicated) {
		this.delicated = delicated;
	}

	@Override
	public String toString() {
		return "Paintings [paintingId=" + paintingId + ", paintingName=" + paintingName + ", artisId=" + artisId
				+ ", largUrl=" + largUrl + ", smallUrl=" + smallUrl + ", price=" + price + ", date=" + date + ", style="
				+ style + ", uploadDate=" + uploadDate + ", period=" + period + ", genre=" + genre + ", media=" + media
				+ ", dimensions=" + dimensions + ", delicated=" + delicated + "]";
	}
	
}
