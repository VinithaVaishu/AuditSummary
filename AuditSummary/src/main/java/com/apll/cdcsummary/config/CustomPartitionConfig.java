package com.apll.cdcsummary.config;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.stereotype.Component;

@Component
public class CustomPartitionConfig implements Partitioner {

	private static final int PARTITION_1 = 0;
	private static final int PARTITION_2 = 1;
	private static final int PARTITION_3 = 2;
	private static final int PARTITION_4 = 3;

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// Custom logic to determine partition based on the key
		// You can use any logic you want to determine the partition
		// For example, you can hash the key and then mod it with the number of
		// partitions
		Integer val1 = (key.toString().hashCode()) % 4000 + 1;
		val1 = Math.abs(val1);
		// System.out.println(val1);
		int partition;
		if (val1 > 0 && val1 <= 1000) {
			partition = PARTITION_1;
		} else if (val1 > 1000 && val1 <= 2000) {
			partition = PARTITION_2;
		} else if (val1 > 2000 && val1 <= 3000) {
			partition = PARTITION_3;
		} else {
			partition = PARTITION_4;
		}

		// int numPartitions = cluster.partitionCountForTopic(topic);
		// return Math.abs(key.hashCode()) % numPartitions;
		return partition;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
