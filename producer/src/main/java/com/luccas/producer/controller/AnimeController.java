package com.luccas.producer.controller;

import com.luccas.producer.model.avro.AnimeMessage;
import com.luccas.producer.model.vo.Anime;
import com.luccas.producer.sender.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime")
public class AnimeController {

	private final KafkaProducer kafkaProducer;

	@Autowired
	public AnimeController(final KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@PostMapping("/post")
	public ResponseEntity<Anime> postarAnime(@RequestBody Anime anime) {
		AnimeMessage animeMessage = new AnimeMessage();
		animeMessage.setName(anime.getName());
		animeMessage.setTipo(anime.getTipo());
		kafkaProducer.sendAnimeMessage(animeMessage);

		return ResponseEntity.ok(anime);
	}

}
