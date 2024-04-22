package com.example.commentservice.service;

import com.example.commentservice.dto.CommentRequestDto;
import com.example.commentservice.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getAll();

    List<CommentResponseDto> getByTaskId(Long taskId);

    CommentResponseDto save(CommentRequestDto request);

    void delete(Long id);

    CommentResponseDto update(Long id, CommentRequestDto request);

}
