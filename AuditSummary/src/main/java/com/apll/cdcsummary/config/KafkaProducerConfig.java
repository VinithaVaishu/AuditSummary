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

import ch.qos.logback.core.net.ssl.SSL;





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
	       // configProps.put(ProducerConfig., JsonSerializer.class);
	        configProps.put(ProducerConfig.ACKS_CONFIG,"1");
	        //configProps.put("ssl.endpoint.identification.algorithm", "https"); 
	        configProps.put("sasl.mechanism", "PLAIN"); 
	        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required "+
	        		"username='TZNHIDR3TZFBPDYO' " +
	        		"password='Jqx/UVRzg2yTpAYh/yLF3iRk7Fejep8dzWkPwzliCRrBLHQReAHqstt9j2VF8m/u';"); 
	        configProps.put("security.protocol", "SASL_PLAINTEXT");
	        configProps.put("ssl.endpoint.identification.algorithm", "https");
	        
	        
	        return new DefaultKafkaProducerFactory<>(configProps);
	    }
	    
	  
	    @Bean
	    public KafkaTemplate<String, String> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	}



