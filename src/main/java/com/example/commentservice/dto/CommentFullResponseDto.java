package com.example.commentservice.dto;

import lombok.Builder;

@Builder
public record CommentFullResponseDto(
        Long idComment, String textComment, Long taskId,
        Long idUser, String nameUser, String secondName, String email
) {
}
