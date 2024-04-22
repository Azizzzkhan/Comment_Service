package com.example.commentservice.kafka.impl;

import com.example.commentservice.dto.CommentFullResponseDto;
import com.example.commentservice.kafka.KafkaSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSenderServiceImpl implements KafkaSenderService {
    private final KafkaSender<String, Object> kafkaSender;
    @Value("${spring.kafka.topic}")
    private String topic;

    public void send(CommentFullResponseDto response) {
        log.info("Вызван метод для отправки сообщений в топик Kafka. Запрос от пользователя: {}", response);
        kafkaSender.send(Mono.just(
                SenderRecord.create(
                        topic,
                        0,
                        System.currentTimeMillis(),
                        String.valueOf(response.hashCode()),
                        response,
                        null
                )
        )).subscribe();
    }
}

