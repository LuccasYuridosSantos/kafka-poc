package com.luccas.producer.config;

import com.luccas.producer.model.avro.AnimeMessage;
import com.luccas.producer.model.avro.CatalogAnimesMessage;
import com.luccas.producer.properties.KafkaProperties;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

	private final KafkaProperties kafkaProperties;

	@Autowired
	public KafkaConfig(final KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	@Bean
	public KafkaAdmin.NewTopics topic() {
		final var topicAnime = TopicBuilder
				.name(kafkaProperties.getTopicAnime())
				.partitions(kafkaProperties.getNumPartitions())
				.replicas(kafkaProperties.getReplicationFactor())
				.compact()
				.build();

		final var topicCatalog = TopicBuilder
				.name(kafkaProperties.getTopicCatalog())
				.partitions(kafkaProperties.getNumPartitions())
				.replicas(kafkaProperties.getReplicationFactor())
				.compact()
				.build();

		return new KafkaAdmin.NewTopics(topicAnime, topicCatalog);
	}

	@Bean
	public KafkaAdmin kafkaAdmin() {
		return new KafkaAdmin(kafkaProducerConfiguration());
	}

	private Map<String, Object> kafkaProducerConfiguration() {

		final Map<String, Object> conf = new HashMap<>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServe());
		conf.put(AdminClientConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());
		conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		conf.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcks());
		conf.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetriesConfig());
		conf.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, kafkaProperties.isEnableIdempotent());
		conf.put(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
				kafkaProperties.getSchemaRegistryUrl());

		return conf;
	}

	@Bean
	public KafkaTemplate<String, CatalogAnimesMessage> kafkaTemplateCatalog() {
		return new KafkaTemplate<>(producerFactoryCatalog());
	}

	@Bean
	public KafkaTemplate<String, AnimeMessage> kafkaTemplateAnime() {
		return new KafkaTemplate<>(producerFactoryAnime());
	}

	@Bean
	public ProducerFactory<String, CatalogAnimesMessage> producerFactoryCatalog() {
		return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration());
	}

	@Bean
	public ProducerFactory<String, AnimeMessage> producerFactoryAnime() {
		return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration());
	}

}
