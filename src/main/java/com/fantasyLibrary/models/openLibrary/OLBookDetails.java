package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OLBookDetails {
	
	@JsonProperty("info_url")
	private String infoUrl;
	
	@JsonProperty("bib_key")
	private String bibKey;
	
	@JsonProperty("preview_url")
	private String previewUrl;
	
	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;
	
	@JsonProperty()
	private Details details;
	
	@JsonProperty()
	private String preview;

	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	public String getBibKey() {
		return bibKey;
	}

	public void setBibKey(String bibKey) {
		this.bibKey = bibKey;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
}
