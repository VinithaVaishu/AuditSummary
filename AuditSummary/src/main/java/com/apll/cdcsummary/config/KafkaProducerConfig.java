package com.apll.cdcsummary.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;





@Configuration
public class KafkaProducerConfig {


	    @Value("${spring.kafka.producer.bootstrap-servers}")
	    private String bootstrapServers;

	    @Bean
	    public ProducerFactory<String, String> producerFactory() {
	        var configProps = new java.util.HashMap<String, Object>();
	        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
	        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	        configProps.put(ProducerConfig.ACKS_CONFIG,"1");
	        return new DefaultKafkaProducerFactory<>(configProps);
	    }
	    
	  
	    @Bean
	    public KafkaTemplate<String, String> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	}



