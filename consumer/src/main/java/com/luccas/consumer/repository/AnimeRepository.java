package com.luccas.consumer.repository;

import com.luccas.consumer.model.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends MongoRepository<Anime, String> {
}
