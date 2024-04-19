package com.example.commentservice.mapper;

import com.example.commentservice.dto.CommentDto;
import com.example.commentservice.entity.Comment;

public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
}
