package com.luccas.producer.sender;

import com.luccas.producer.model.avro.AnimeMessage;
import com.luccas.producer.model.avro.CatalogAnimesMessage;
import com.luccas.producer.properties.KafkaProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Log4j2
public class KafkaProducer {

	private final KafkaTemplate<String, AnimeMessage> animeKafkaTemplate;
	private final KafkaTemplate<String, CatalogAnimesMessage> catalogKafkaTemplate;
	private KafkaProperties kafkaProperties;

	@Autowired
	public KafkaProducer(final KafkaTemplate<String, AnimeMessage> animeKafkaTemplate,
			final KafkaTemplate<String, CatalogAnimesMessage> catalogKafkaTemplate, final KafkaProperties kafkaProperties) {
		this.animeKafkaTemplate = animeKafkaTemplate;
		this.catalogKafkaTemplate = catalogKafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}

	public void sendAnimeMessage(final AnimeMessage animeMessage) {
			final ProducerRecord<String, AnimeMessage> producerRecord =
					new ProducerRecord<>(
							kafkaProperties.getTopicAnime(),
							String.format("anime-%s-%s",animeMessage.getName(), Instant.now().toString()),
							animeMessage
					);
		 this.animeKafkaTemplate.send(producerRecord);
		 log.info("Anime Message send for kafka: {}", animeMessage);


	}

	public void sendCatalogMessage(final CatalogAnimesMessage catalogAnimesMessage) {


		final ProducerRecord<String, CatalogAnimesMessage> producerRecord =
				new ProducerRecord<>(
						kafkaProperties.getTopicCatalog(),
						String.format("catalog-%s", Instant.now().toString()),
						catalogAnimesMessage
				);

		this.catalogKafkaTemplate.send(producerRecord);
		log.info("Catalog Message send for kafka: {}", catalogAnimesMessage);
	}
}
