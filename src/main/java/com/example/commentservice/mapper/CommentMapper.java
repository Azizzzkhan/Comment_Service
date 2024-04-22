package com.example.commentservice.mapper;

import com.example.commentservice.dto.CommentFullResponseDto;
import com.example.commentservice.dto.CommentRequestDto;
import com.example.commentservice.dto.CommentResponseDto;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.entity.User;

public interface CommentMapper {
    CommentResponseDto toDto(Comment comment);

    CommentFullResponseDto toDto(CommentResponseDto response, User user);

    Comment toEntity(CommentRequestDto request);
}
