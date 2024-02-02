package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    private List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    private Comment getComment(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping(path = "")
    private ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentRepository.save(comment));
    }

    @PutMapping(path = "/{id}")
    private Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        var foundComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        foundComment.setBody(comment.getBody() != null? comment.getBody() : foundComment.getBody());
        foundComment.setPostId(comment.getPostId());
        return commentRepository.save(foundComment);
    }

    @DeleteMapping(path = "/{id}")
    private void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
// END
