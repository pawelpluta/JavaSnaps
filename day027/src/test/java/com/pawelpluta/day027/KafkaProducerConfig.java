package com.pawelpluta.day027;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaProducerConfig {

    @Value("${kafka.orders.bootstrapServers}")
    private String ticketsBootstrapServers;

    @Bean
    public Map<String, Object> orderPlacedProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ticketsBootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, OrderPlacedEvent> orderPlacedProducerFactory() {
        return new DefaultKafkaProducerFactory<>(orderPlacedProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, OrderPlacedEvent> orderPlacedKafkaTemplate() {
        return new KafkaTemplate<>(orderPlacedProducerFactory());
    }
}
