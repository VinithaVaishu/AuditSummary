package com.apll.cdcsummary.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.apll.cdcsummary.constants.AppConstants;
import com.apll.cdcsummary.model.CDCSummaryResponse;
import com.apll.cdcsummary.util.AppUtils;

import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.*;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CDCSummaryPoller {

	@Autowired
	private KafkaPublisher kafkaPublisher;

	@Value("${CDC.summary.api.username}")
	private String username;
	@Value("${CDC.summary.api.password}")
	private String password;
	@Value("${CDC.summary.api.base_url}")
	private String baseurl;
	@Value("${CDC.summary.api.path}")
	private String path;
	@Value("${CDC.summary.api.responseformat}")
	private String responseFormat;

	@Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
	public void getSummaryData() throws IOException, InterruptedException, ExecutionException {
		log.info("Summary poller has started");
		ExchangeFilterFunction basicAuthenticationFilter = ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			ClientRequest newRequest = ClientRequest.from(clientRequest).headers(headers -> {
				headers.setBasicAuth(username, password);
			}).build();
			return Mono.just(newRequest);
		});

		String afterLsn = AppUtils.readStringFromFile();
		log.info("Last LSN from previoud poll :"+ afterLsn);
		WebClient client = WebClient.builder().baseUrl(baseurl).filter(basicAuthenticationFilter).build();
		log.info("Polling of Cargowise summary API started");
		List<CDCSummaryResponse> cdcResponseList = client.get()
				.uri(uriBuilder -> uriBuilder.path(path).queryParam(AppConstants.responseFormat, responseFormat)
						.queryParam(AppConstants.after_lsn, afterLsn).build())
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(CDCSummaryResponse.class).collectList()
				.block();

		log.info("Polling of Cargowise summary API ended");
		log.info("No of records polled from current polling : "+ cdcResponseList.size());
		
		/*// Poll summary response with date range
		 * List<ChangedTable> changedTables = client.get() .uri(uriBuilder ->
		 * uriBuilder.path("/Services/api/analytics/audit-data-summary")
		 * .queryParam("response_format", "JSON").queryParam("from_date", "20240101")
		 * .queryParam("to_date", "20240131").build()) .header("Cookie",
		 * "WEBSVC=58b7de25b1e11abb")
		 * .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(ChangedTable.class)
		 * .collectList().block();
		 */
		kafkaPublisher.sendMessage(cdcResponseList);

	}
}
