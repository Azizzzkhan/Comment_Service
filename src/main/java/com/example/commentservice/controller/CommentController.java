package com.example.commentservice.controller;

import com.example.commentservice.dto.CommentDto;
import com.example.commentservice.mapper.CommentMapper;
import com.example.commentservice.mapper.impl.CommentMapperImpl;
import com.example.commentservice.service.impl.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/api/comment")

public class CommentController {
    private final CommentServiceImpl commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
        this.commentMapper = new CommentMapperImpl();
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getByTaskId(taskId));
    }

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.save(commentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.update(id, commentDto));
    }
}
