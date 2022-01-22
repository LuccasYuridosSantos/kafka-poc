package com.luccas.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

	private String clientId;
	private String bootStrapServe;
	private String schemaRegistryUrl;
	private String acks;
	private String topicAnime;
	private String topicCatalog;
	private int numPartitions;
	private int replicationFactor;
	private int retriesConfig;
	private boolean enableIdempotent;
	private String compression;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public String getBootStrapServe() {
		return bootStrapServe;
	}

	public void setBootStrapServe(final String bootStrapServe) {
		this.bootStrapServe = bootStrapServe;
	}

	public String getSchemaRegistryUrl() {
		return schemaRegistryUrl;
	}

	public void setSchemaRegistryUrl(final String schemaRegistryUrl) {
		this.schemaRegistryUrl = schemaRegistryUrl;
	}

	public String getAcks() {
		return acks;
	}

	public void setAcks(final String acks) {
		this.acks = acks;
	}

	public String getTopicAnime() {
		return topicAnime;
	}

	public void setTopicAnime(final String topicAnime) {
		this.topicAnime = topicAnime;
	}

	public String getTopicCatalog() {
		return topicCatalog;
	}

	public void setTopicCatalog(final String topicCatalog) {
		this.topicCatalog = topicCatalog;
	}

	public int getNumPartitions() {
		return numPartitions;
	}

	public void setNumPartitions(final int numPartitions) {
		this.numPartitions = numPartitions;
	}

	public int getReplicationFactor() {
		return replicationFactor;
	}

	public void setReplicationFactor(final int replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

	public int getRetriesConfig() {
		return retriesConfig;
	}

	public void setRetriesConfig(final int retriesConfig) {
		this.retriesConfig = retriesConfig;
	}

	public boolean isEnableIdempotent() {
		return enableIdempotent;
	}

	public void setEnableIdempotent(final boolean enableIdempotent) {
		this.enableIdempotent = enableIdempotent;
	}

	public String getCompression() {
		return compression;
	}

	public void setCompression(final String compression) {
		this.compression = compression;
	}
}
