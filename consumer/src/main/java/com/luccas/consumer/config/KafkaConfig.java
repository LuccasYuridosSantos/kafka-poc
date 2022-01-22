package com.luccas.consumer.config;

import com.luccas.consumer.properties.KafkaProperties;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Log4j2
public class KafkaConfig {
	
	private KafkaProperties kafkaProperties;

	@Autowired
	public KafkaConfig(final KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	@Bean
	public KafkaAdmin kafkaAdmin() {
		return new KafkaAdmin(getCommonKafkaConsumerConfigurations());
	}

	private Map<String, Object> getCommonKafkaConsumerConfigurations() {
		final Map<String, Object> conf = new HashMap<>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServer());
		conf.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
		conf.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		conf.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		conf.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, false);
		conf.put(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
				kafkaProperties.getSchemaRegistryUrl());

		return conf;
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, GenericRecord> containerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, GenericRecord> factory =
				new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory());

		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, GenericRecord> consumerFactory() {

		return new DefaultKafkaConsumerFactory(
				getCommonKafkaConsumerConfigurations(),
				new StringDeserializer(),
				new KafkaAvroDeserializer());
	}
}
