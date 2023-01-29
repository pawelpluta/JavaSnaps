package com.pawelpluta.day027;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
class KafkaConfig {
    @Value("${kafka.orders.bootstrapServers}")
    private String orderBootstrapServers;
    @Value("${kafka.orders.groupId}")
    private String ordersConsumerGroupId;
    @Value("${kafka.orders.offset}")
    private String ordersOffset;

    @Value("${kafka.reservations.bootstrapServers}")
    private String reservationsBootstrapServers;
    @Value("${kafka.reservations.groupId}")
    private String reservationsConsumerGroupId;
    @Value("${kafka.reservations.offset}")
    private String reservationsOffset;

    @Bean
    public Map<String, Object> ordersConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, orderBootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ordersConsumerGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, ordersOffset);
        return props;
    }

    @Bean
    public ConsumerFactory<String, OrderPlacedEvent> ordersConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                ordersConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(OrderPlacedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ordersConsumerFactory());
        return factory;
    }

    @Bean
    public Map<String, Object> reservationsConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, reservationsBootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, reservationsConsumerGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, reservationsOffset);
        return props;
    }

    @Bean
    public ConsumerFactory<String, WarehouseProductReservedEvent> reservationsConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                ordersConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(WarehouseProductReservedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, WarehouseProductReservedEvent> reservationsKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, WarehouseProductReservedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reservationsConsumerFactory());
        return factory;
    }

    @Bean
    public Map<String, Object> reservationsProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, reservationsBootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, WarehouseProductReservedEvent> reservationsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(reservationsProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, WarehouseProductReservedEvent> reservationsKafkaTemplate() {
        return new KafkaTemplate<>(reservationsProducerFactory());
    }

}
