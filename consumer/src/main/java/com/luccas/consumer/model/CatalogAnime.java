package com.luccas.consumer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CatalogAnime {

	private String id;
	private List<Anime> animesList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public List<Anime> getAnimesList() {
		return animesList;
	}

	public void setAnimesList(final List<Anime> animesList) {
		this.animesList = animesList;
	}

	@Override public boolean equals(final Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		final CatalogAnime that = (CatalogAnime) o;

		return new EqualsBuilder().append(id, that.id).append(animesList, that.animesList).isEquals();
	}

	@Override public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(animesList).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("animeList", animesList).toString();
	}
}
