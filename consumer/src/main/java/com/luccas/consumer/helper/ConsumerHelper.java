package com.luccas.consumer.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luccas.consumer.model.Anime;
import com.luccas.consumer.model.CatalogAnime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsumerHelper {

	public static Anime convertAvroToAnimeObject(final String value) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		try {
			log.info("Converting avro order message with payload '{}'", value);
			return mapper.readValue(value, Anime.class);
		} catch (final JsonProcessingException e) {
			log.error("Error on deserialization order avro message {}", value);
			throw new IllegalArgumentException(e);
		}

	}

	public static CatalogAnime convertAvroToCatalogObject(final String value) {
		return convertObject(value, CatalogAnime.class);
	}

	private static <T> T convertObject(String value, Class<T> valueType){
		final ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		try {
			log.debug("Converting avro order message with payload '{}'", value);
			return mapper.readValue(value, valueType);
		} catch (final JsonProcessingException e) {
			log.error("Error on deserialization order avro message {}", value);
			throw new IllegalArgumentException(e);
		}
	}
}
