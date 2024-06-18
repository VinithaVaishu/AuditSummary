package com.apll.auditSummary.service;

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

import com.apll.auditSummary.config.CustomPartitionConfig;
import com.apll.auditSummary.util.AppUtils;

@Service
public class KafkaPublisher {

	@Autowired
	private KafkaTemplate<String, String> template;

	@Value("${kafka.topic}")
	private String summaryTopic;
	
	private CustomPartitionConfig partitionconfig;

	public void sendMessage(List<ChangedTable> list) throws InterruptedException, ExecutionException, IOException {
//		for (ChangedTable changedTable : list) {
//			template.send("com.apll.cargowise.summary", changedTable.getChangedTableName(), changedTable.toString());
//		System.out.println(changedTable.toString());
//		}
		Long start = System.nanoTime();
		template.send("com.apll.cargowise.summary", "Tablename_key", "Value");
//		Long count =list.stream().map(
//				table -> template.send("com.apll.cargowise.summary", table.getChangedTableName(), table.toString()))
//				.count();
 // System.out.println(count);
		 
		String afterLsn =null;
		if(list.size()!=0) {
			afterLsn = list.get(list.size()-1).getLsn();
			System.out.println("No of records processed " + list.size() + "  " + "Last LSN = " + afterLsn);

			AppUtils.writingGivenStringToFile(afterLsn);
		}else {
			System.out.println("No of records processed " + list.size() );
		}
		

		Long endTime = System.nanoTime();
		System.out.println(endTime - start);// 587001//1164400

	}

}
