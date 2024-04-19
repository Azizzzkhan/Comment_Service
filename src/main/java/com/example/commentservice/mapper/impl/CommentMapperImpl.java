package com.example.commentservice.mapper.impl;

import com.example.commentservice.dto.CommentDto;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.mapper.CommentMapper;

public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(), comment.getTaskId());
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        return new Comment(commentDto.id(), commentDto.text(), commentDto.taskId());
    }
}
