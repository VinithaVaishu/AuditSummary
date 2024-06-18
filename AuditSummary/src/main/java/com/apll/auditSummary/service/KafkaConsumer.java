package com.apll.auditSummary.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	public static String afterLsn;
	//@KafkaListener(topics = "lsn.topic", 
     //       groupId = "group_id")
    public static void listen(ConsumerRecord<String, String> record) {
		afterLsn=record.value();
        System.out.println("Received message: " + record.value());
    }

}
