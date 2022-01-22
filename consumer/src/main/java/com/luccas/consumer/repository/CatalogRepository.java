package com.luccas.consumer.repository;

import com.luccas.consumer.model.CatalogAnime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<CatalogAnime, String> {
}
