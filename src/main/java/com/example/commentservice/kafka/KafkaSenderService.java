package com.example.commentservice.kafka;
import com.example.commentservice.dto.CommentFullResponseDto;

public interface KafkaSenderService {
    void send(CommentFullResponseDto response);
}
