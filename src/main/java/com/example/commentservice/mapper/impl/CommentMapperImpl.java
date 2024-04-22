package com.example.commentservice.mapper.impl;

import com.example.commentservice.dto.CommentFullResponseDto;
import com.example.commentservice.dto.CommentRequestDto;
import com.example.commentservice.dto.CommentResponseDto;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.entity.User;
import com.example.commentservice.mapper.CommentMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentResponseDto toDto(Comment comment) {
        return CommentResponseDto.builder()
                .idComment(comment.getId())
                .textComment(comment.getText())
                .taskId(comment.getTaskId()).build();
    }

    @Override
    public CommentFullResponseDto toDto(CommentResponseDto response, User user) {
        return CommentFullResponseDto.builder()
                .idComment(response.idComment())
                .textComment(response.textComment())
                .taskId(response.taskId())
                .idUser(user.getId())
                .nameUser(user.getName())
                .secondName(user.getSecondName())
                .email(user.getEmail()).build();
    }

    @Override
    public Comment toEntity(CommentRequestDto request) {
        return Comment.builder()
                .text(request.textComment())
                .taskId(request.taskId()).build();
    }
}
