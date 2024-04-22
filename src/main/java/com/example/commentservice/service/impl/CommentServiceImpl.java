package com.example.commentservice.service.impl;
import com.example.commentservice.dto.CommentRequestDto;
import com.example.commentservice.dto.CommentResponseDto;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.CommentException;
import com.example.commentservice.mapper.CommentMapper;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public List<CommentResponseDto> getAll() {
        return commentRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getByTaskId(Long taskId) {
        Optional<List<Comment>> comments = commentRepository.findAllByTaskId(taskId);
        return comments.map(commentList -> commentList.stream().map(mapper::toDto)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public CommentResponseDto save(CommentRequestDto request) {
        Comment comment = mapper.toEntity(request);
        Comment result = commentRepository.save(comment);
        return mapper.toDto(result);
    }

    @Override
    public void delete(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }

    @Override
    public CommentResponseDto update(Long id, CommentRequestDto request) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setText(request.textComment());
                    return mapper.toDto(commentRepository.save(comment));
                }).orElseGet(() -> mapper.toDto(commentRepository.save(mapper.toEntity(request))));
    }


    private Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new CommentException("Comment with id " + id + " not found"));
    }
}
