package com.luccas.consumer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

	private String groupId;
	private String bootStrapServer;
	private String schemaRegistryUrl;
	private String topicAnime;
	private String topicCatalog;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	public String getBootStrapServer() {
		return bootStrapServer;
	}

	public void setBootStrapServer(final String bootStrapServer) {
		this.bootStrapServer = bootStrapServer;
	}

	public String getSchemaRegistryUrl() {
		return schemaRegistryUrl;
	}

	public void setSchemaRegistryUrl(final String schemaRegistryUrl) {
		this.schemaRegistryUrl = schemaRegistryUrl;
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

}
