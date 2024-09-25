package bean;

public class Artis {
	
	private String artisId;
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
