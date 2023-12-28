package com.example.consumers_two.contronller;

import com.example.consumers_two.constants.KafkaConstants;
import com.example.consumers_two.entity.Users;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/read")
public class ReadAllMessage {

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @GetMapping
    public List<Users> readPartition() {
        Gson gson = new Gson();
        // Tạo Kafka consumer từ consumer factory đã được cấu hình từ properties
        Consumer<String, String> consumer = consumerFactory.createConsumer();

        // Lấy thông tin partition cho topic
        int partitionIndex = 0; // Chọn partition đầu tiên
        List<PartitionInfo> partitions = consumer.partitionsFor(KafkaConstants.CONFIG_TOPIC_KHACH_HANH);
        PartitionInfo partitionInfo = partitions.get(partitionIndex);

        // Đặt offset về đầu partition
        TopicPartition topicPartition = new TopicPartition(KafkaConstants.CONFIG_TOPIC_KHACH_HANH, partitionInfo.partition());
        consumer.assign(Collections.singletonList(topicPartition));
        consumer.seekToBeginning(Collections.singletonList(topicPartition));

        List<Users> list = new ArrayList<>();
        // Lặp qua các message trong partition và hiển thị chúng
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100)); // timeout 100 milliseconds

            // Kiểm tra xem đã đọc hết message trong partition chưa
            if (records.isEmpty()) {
                break;
            }

            records.forEach(record -> {
                Users users = gson.fromJson(record.value(), Users.class);
                list.add(users);
            });
        }

        // Đóng consumer khi đã xong
        consumer.close();
        return list;
    }
}
