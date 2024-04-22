package com.example.commentservice.dto;

import lombok.Builder;

@Builder
public record CommentRequestDto(
        String textComment, Long taskId
) {

}
