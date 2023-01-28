package com.pawelpluta.day027;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

interface KafkaSupport {
    KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.1.9"));

    @DynamicPropertySource
    static void setupKafka(DynamicPropertyRegistry propertyRegistry) {
        KAFKA_CONTAINER.start();
        propertyRegistry.add("kafka.orders.bootstrapServers", KAFKA_CONTAINER::getBootstrapServers);
        propertyRegistry.add("kafka.reservations.bootstrapServers", KAFKA_CONTAINER::getBootstrapServers);
        createTopic("orders-topic");
        createTopic("reservations-topic");
    }

    private static void createTopic(String topicName) {
        try (var admin = AdminClient.create(Map.of(BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONTAINER.getBootstrapServers()))) {
            admin.createTopics(List.of(new NewTopic(topicName, 1, (short) 1)));
        }
    }
}
