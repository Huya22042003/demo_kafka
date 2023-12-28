package com.example.consumers_two.kafka.listen;

import com.example.consumers_two.constants.KafkaConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class UserListen {

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.CONFIG_TOPIC_KHACH_HANH, partitions = {"0"}))
    public void listenToPartition0(String message) {
        System.out.println("Received Message from Partition 0: " + message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.CONFIG_TOPIC_KHACH_HANH, partitions = {"1"}))
    public void listenToPartition1(String message) {
        System.out.println("Received Message from Partition 1: " + message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.CONFIG_TOPIC_KHACH_HANH, partitions = {"2"}))
    public void listenToPartition2(String message) {
        System.out.println("Received Message from Partition 2: " + message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.CONFIG_TOPIC_KHACH_HANH, partitions = {"3"}))
    public void listenToPartition3(String message) {
        System.out.println("Received Message from Partition 3: " + message);
    }
}
