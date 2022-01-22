package com.luccas.consumer.service;

import com.luccas.consumer.model.CatalogAnime;
import com.luccas.consumer.repository.CatalogRepository;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Log4j2
public class CatalogService {

	private MongoOperations mongoOperations;
	private CatalogRepository catalogRepository;

	@Autowired
	public CatalogService(final MongoOperations mongoOperations, final CatalogRepository catalogRepository) {
		this.mongoOperations = mongoOperations;
		this.catalogRepository = catalogRepository;
	}

	public CatalogAnime insertCatalog(final CatalogAnime catalogAnime) {
		try {
			if(!ObjectUtils.isEmpty(catalogAnime)){
				log.debug("Catalog is being inserted");
				return mongoOperations.insert(catalogAnime, "CatalogAnimes");
			}
		}catch (final Exception e) {
			log.error("Not possible insert anime in database, with message error: {}", e.getMessage());
		}

		return null;
	}
}
