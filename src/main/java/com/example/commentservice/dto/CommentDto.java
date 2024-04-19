package com.example.commentservice.dto;

public record CommentDto(
        Long id, String text, Long taskId
) {

}
