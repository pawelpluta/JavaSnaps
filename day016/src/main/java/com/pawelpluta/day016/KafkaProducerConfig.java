package com.pawelpluta.day016;

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

    @Value("${kafka.tickets.bootstrapServers}")
    private String ticketsBootstrapServers;

    @Value("${kafka.seats.bootstrapServers}")
    private String seatsBootstrapServers;

    @Bean
    public Map<String, Object> ticketsProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ticketsBootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, TicketSold> ticketsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(ticketsProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, TicketSold> ticketsKafkaTemplate() {
        return new KafkaTemplate<>(ticketsProducerFactory());
    }

    @Bean
    public Map<String, Object> seatsProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, seatsBootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, SeatReserved> seatsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(seatsProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, SeatReserved> seatsKafkaTemplate() {
        return new KafkaTemplate<>(seatsProducerFactory());
    }
}
