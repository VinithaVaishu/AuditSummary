package com.apll.auditSummary;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.apll.auditSummary.service.ChangedTable;
import com.apll.auditSummary.service.KafkaPublisher;
import com.apll.auditSummary.util.AppUtils;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableKafka
public class AuditSummaryApplication {

	@Autowired
	private KafkaPublisher kafkaPublisher;

	public static void main(String[] args) {
		SpringApplication.run(AuditSummaryApplication.class, args);
	}

	@PostConstruct
	public void getSummaryData() throws IOException, InterruptedException, ExecutionException {
//		ExchangeFilterFunction basicAuthenticationFilter = ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//			ClientRequest newRequest = ClientRequest.from(clientRequest).headers(headers -> {
//				headers.add("Cookie", "WEBSVC=58b7de25b1e11abb");
//				headers.setBasicAuth("A0P.Audit.API.APLL", "AHPeD5rp8eN2Xj9Eqq*3rH");
//			}).build();
//			return Mono.just(newRequest);
//		});
//
//		String afterLsn = AppUtils.readStringFromFile();
//		System.out.println(" Start LSN "+afterLsn);
//		WebClient client = WebClient.builder().baseUrl("https://svc-a0ptrn.wisegrid.net")
//				.filter(basicAuthenticationFilter).build();
//
//		
//		
//		  List<ChangedTable> changedTables = client.get() .uri(uriBuilder ->
//		  uriBuilder.path("/Services/api/analytics/audit-data-summary")
//		  .queryParam("response_format", "JSON").queryParam("after_lsn",
//		  afterLsn).build()).accept(MediaType.APPLICATION_JSON).retrieve()
//		  .bodyToFlux(ChangedTable.class).collectList().block();
//		 
//		 
//		
//		/*
//		 * List<ChangedTable> changedTables = client.get() .uri(uriBuilder ->
//		 * uriBuilder.path("/Services/api/analytics/audit-data-summary")
//		 * .queryParam("response_format", "JSON").queryParam("from_date", "20240101")
//		 * .queryParam("to_date", "20240131").build()) .header("Cookie",
//		 * "WEBSVC=58b7de25b1e11abb")
//		 * .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(ChangedTable.class)
//		 * .collectList().block();
//		 */
//		 
//		 
		kafkaPublisher.sendMessage(null);

	}
}