package com.luccas.consumer.service;

import com.luccas.consumer.model.Anime;
import com.luccas.consumer.repository.AnimeRepository;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AnimeService {

	private AnimeRepository animeRepository;
	private MongoOperations mongoOperations;

	@Autowired
	public AnimeService(final AnimeRepository animeRepository, final MongoOperations mongoOperations) {
		this.animeRepository = animeRepository;
		this.mongoOperations = mongoOperations;
	}


	public Anime insertAnime(final Anime anime) {

		try {
			return mongoOperations.insert(anime, "AnimeCollection");
		}catch (MongoException e) {
			log.error("Not possible insert anime in database, with message error: {}", e.getMessage());
		}
		return null;
	}
}
