package com.apll.cdcsummary;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.apll.cdcsummary.model.CDCSummaryResponse;
import com.apll.cdcsummary.service.KafkaPublisher;
import com.apll.cdcsummary.util.AppUtils;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class AuditSummaryApplication {


	public static void main(String[] args) {
		SpringApplication.run(AuditSummaryApplication.class, args);
	}


}