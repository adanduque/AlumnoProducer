package com.example.alumnonotas.config;

import com.example.alumnonotas.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.backoff.FixedBackOff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaProducerConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    public KafkaProducerConfig(){

    }
    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        return new DefaultKafkaProducerFactory<>(this.kafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Event<?>>>kafkaListenerContainerFactory() throws IOException {
        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(0L, 0L)));
        return factory;
    }

}