package com.example.commentservice.controller;

import com.example.commentservice.dto.CommentFullResponseDto;
import com.example.commentservice.dto.CommentRequestDto;
import com.example.commentservice.dto.CommentResponseDto;
import com.example.commentservice.entity.User;
import com.example.commentservice.kafka.KafkaSenderService;
import com.example.commentservice.mapper.CommentMapper;
import com.example.commentservice.service.CommentService;
import com.example.commentservice.util.TokenDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final TokenDecoder tokenDecoder;
    private final KafkaSenderService kafkaSenderService;

    @GetMapping()
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getByTaskId(taskId));
    }

    @PostMapping()
    public ResponseEntity<CommentFullResponseDto> createComment(@RequestBody CommentRequestDto request, @RequestHeader HttpHeaders headers) {
        User user = tokenDecoder.getUserData(headers);
        CommentResponseDto commentResponse = commentService.save(request);
        CommentFullResponseDto response = commentMapper.toDto(commentResponse, user);
        kafkaSenderService.send(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto request) {
        return ResponseEntity.ok(commentService.update(id, request));
    }
}
