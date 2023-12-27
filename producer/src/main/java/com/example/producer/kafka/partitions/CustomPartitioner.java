package com.example.producer.kafka.partitions;

import com.example.producer.constants.PartitionsConstant;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (key.equals(PartitionsConstant.PARTITIONS_KEY_USER))
            return 0;
        if (key.equals(PartitionsConstant.PARTITIONS_KEY_TEACHER))
            return 1;
        if (key.equals(PartitionsConstant.PARTITIONS_KEY_CLASS))
            return 2;
        return -1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
