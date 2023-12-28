package com.example.producer.kafka.producer;

import com.example.producer.constants.KafkaConstants;
import com.example.producer.constants.PartitionsConstant;
import com.example.producer.entity.Users;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ProducerSendMessage {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Users users) {
        Gson gson = new Gson();
        CompletableFuture<SendResult<String, String>> future = getPartitionByRole(users);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + gson.toJson(users) +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        gson.toJson(users) + "] due to : " + ex.getMessage());
            }
        });
    }

    public CompletableFuture<SendResult<String, String>> getPartitionByRole(Users users) {
        Gson gson = new Gson();
        if (users.getUserRole().equals(0L))
            return kafkaTemplate
                    .send(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, PartitionsConstant.PARTITIONS_KEY_USER, gson.toJson(users));
        if (users.getUserRole().equals(1L))
            return kafkaTemplate
                    .send(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, PartitionsConstant.PARTITIONS_KEY_TEACHER, gson.toJson(users));
        if (users.getUserRole().equals(2L))
            return kafkaTemplate
                    .send(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, PartitionsConstant.PARTITIONS_KEY_CLASS, gson.toJson(users));
        if (users.getUserRole().equals(3L))
            return kafkaTemplate
                    .send(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, PartitionsConstant.PARTITIONS_KEY_ADMIN, gson.toJson(users));
        return kafkaTemplate
                .send(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, PartitionsConstant.PARTITIONS_KEY_USER, gson.toJson(users));
    }
}
