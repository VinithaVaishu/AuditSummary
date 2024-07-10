package com.apll.cdcsummary.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.apll.cdcsummary.config.CustomPartitionConfig;
import com.apll.cdcsummary.model.CDCSummaryResponse;
import com.apll.cdcsummary.util.AppUtils;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaPublisher {

	@Autowired
	private KafkaTemplate<String, String> template;

	@Value("${kafka_topic}")
	private String summaryTopic;

	private CustomPartitionConfig partitionconfig;

	public void sendMessage(List<CDCSummaryResponse> list)
			throws InterruptedException, ExecutionException, IOException {
		log.info("Processing of Summary records has started");
		Long start = System.currentTimeMillis();
		List<String> result = list.stream().map(cdcResponse -> publish(cdcResponse)).collect(Collectors.toList());
		// publish(list.get(0));
		String afterLsn = null;
		if (list.size() != 0) {
			afterLsn = list.get(list.size() - 1).getLsn();
			log.info("No of records processed " + list.size() + "  " + "Last LSN = " + afterLsn);

			AppUtils.writingGivenStringToFile(afterLsn);
		} else {
			log.info("No of records processed " + list.size());
		}

		Long endTime = System.currentTimeMillis();
		log.debug("Time taken to process the summary records"+(endTime - start));// 587001//1164400
		log.info("Processing of Summary records has ended");

	}

	private String publish(CDCSummaryResponse cdcResponse) {
		CompletableFuture<SendResult<String, String>> result = template.send("cdr-summary-topic-01",
				cdcResponse.getChangedTableName(), cdcResponse.toString());
		return result.toString();
	}

}
