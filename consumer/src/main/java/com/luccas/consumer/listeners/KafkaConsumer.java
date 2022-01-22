package com.luccas.consumer.listeners;

import com.luccas.consumer.helper.ConsumerHelper;
import com.luccas.consumer.model.Anime;
import com.luccas.consumer.service.AnimeService;
import com.luccas.consumer.service.CatalogService;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@Log4j2
public class KafkaConsumer {

	private AnimeService animeService;
	private CatalogService catalogService;

	@Autowired
	public KafkaConsumer(final AnimeService animeService, final  CatalogService catalogService) {
		this.animeService = animeService;
		this.catalogService= catalogService;
	}

	@KafkaListener(topics = "${kafka.topicAnime}", groupId = "${kafka.groupId}", containerFactory = "containerFactory")
	public void receiveAnime(@Payload final ConsumerRecord<String, GenericRecord> payload) {

		try {
			final Anime anime = ConsumerHelper.convertAvroToAnimeObject(payload.value().toString());

				log.info("Received message kafka {}", anime.toString());
				var result = this.animeService.insertAnime(anime);

				if(!ObjectUtils.isEmpty(result)){
					log.info("Anime inserted in database with successfully: {}", result);
				}

		} catch (final Exception e) {
			log.debug("Error : {}", e.getMessage());
		}
	}

	@KafkaListener(topics = "${kafka.topicCatalog}", groupId = "${kafka.groupId}", containerFactory = "containerFactory")
	public void receiveCatalog(@Payload final ConsumerRecord<String, GenericRecord> payload) {

		System.out.println(payload.value());
		try {
			final var catalog = ConsumerHelper.convertAvroToCatalogObject(payload.value().toString());

			log.info("Received message kafka {}", catalog.toString());
			var result = this.catalogService.insertCatalog(catalog);

			if(!ObjectUtils.isEmpty(result)){
				log.info("Anime inserted in database with successfully: {}", result);
			}

		} catch (final Exception e) {
			log.debug("Error : {}", e.getMessage());
		}
	}

}
