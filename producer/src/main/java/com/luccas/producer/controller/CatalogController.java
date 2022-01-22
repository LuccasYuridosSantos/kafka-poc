package com.luccas.producer.controller;

import com.luccas.producer.model.avro.AnimeMessage;
import com.luccas.producer.model.avro.CatalogAnimesMessage;
import com.luccas.producer.model.vo.CatalogAnime;
import com.luccas.producer.sender.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

	private KafkaProducer kafkaProducer;

	@Autowired
	public CatalogController(final KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@PostMapping("/animes")
	public ResponseEntity<CatalogAnime> postCatalog(@RequestBody CatalogAnime catalogAnime) {
		final CatalogAnimesMessage message = new CatalogAnimesMessage();
		final List<AnimeMessage> messageList = new ArrayList<>();

		message.setId(catalogAnime.getId());

		catalogAnime.getAnimeList().forEach(
				anime -> {

					final AnimeMessage animeMessage = new AnimeMessage();

					animeMessage.setName(anime.getName());
					animeMessage.setTipo(anime.getTipo());

					messageList.add(animeMessage);
				}
		);

		message.setAnimesList(messageList);


		kafkaProducer.sendCatalogMessage(message);

		return ResponseEntity.ok(catalogAnime);
	}
}
