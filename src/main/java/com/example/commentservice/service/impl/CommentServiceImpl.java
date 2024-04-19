package com.example.commentservice.service.impl;

import com.example.commentservice.dto.CommentDto;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.CommentException;
import com.example.commentservice.mapper.CommentMapper;
import com.example.commentservice.mapper.impl.CommentMapperImpl;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private CommentMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.mapper = new CommentMapperImpl();
    }


    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getByTaskId(Long taskId) {
        Optional<List<Comment>> comments = commentRepository.findAllByTaskId(taskId);
        return comments.map(commentList -> commentList.stream().map(mapper::toDto).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        return mapper.toDto(commentRepository.save(mapper.toEntity(commentDto)));
    }

    @Override
    public void delete(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setText(commentDto.text());
                    return mapper.toDto(commentRepository.save(comment));
                }).orElseGet(() -> mapper.toDto(commentRepository.save(mapper.toEntity(commentDto))));
    }


    private Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment with id " + id + " not found"));
    }
}
