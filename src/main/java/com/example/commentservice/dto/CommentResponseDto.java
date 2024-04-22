package com.example.commentservice.dto;

import lombok.Builder;

@Builder
public record CommentResponseDto(
        Long idComment, String textComment, Long taskId
) {
}
