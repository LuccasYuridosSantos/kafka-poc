package com.luccas.producer.model.vo;

import java.util.List;

public class CatalogAnime {

	private String id;
	private List<Anime> animeList;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public List<Anime> getAnimeList() {
		return animeList;
	}

	public void setAnimeList(final List<Anime> animeList) {
		this.animeList = animeList;
	}
}
