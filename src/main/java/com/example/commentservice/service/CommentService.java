package com.example.commentservice.service;

import com.example.commentservice.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();

    List<CommentDto> getByTaskId(Long taskId);

    CommentDto save(CommentDto commentDto);

    void delete(Long id);

    CommentDto update(Long id, CommentDto commentDto);


}
