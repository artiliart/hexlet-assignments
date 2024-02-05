package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostsController {

     private final CommentRepository commentRepository;
     private final PostRepository postRepository;

     @GetMapping(path = "")
     private List<PostDTO> getAllPosts() {
         var posts = postRepository.findAll();
         return posts.stream()
                 .map(i -> {
                     var mappedPost = new PostDTO();
                     mappedPost.setId(i.getId());
                     mappedPost.setTitle(i.getTitle());
                     mappedPost.setBody(i.getBody());
                     mappedPost.setComments(commentRepository.findByPostId(i.getId())
                             .stream().map(this::mapToDto).collect(Collectors.toList()));
                     return mappedPost;
                 }).toList();
     }

     @GetMapping(path = "/{id}")
     private PostDTO getPostById(@PathVariable Long id) {
         var post = postRepository.findById(id)
                 .orElseThrow(() ->  new ResourceNotFoundException("Post with id " + id + " not found"));
         var postDto = new PostDTO();
         postDto.setId(post.getId());
         postDto.setBody(post.getBody());
         postDto.setTitle(post.getTitle());
         postDto.setComments(commentRepository.findByPostId(post.getId()).stream().map(this::mapToDto).collect(Collectors.toList()));
         return postDto;
     }

     private CommentDTO mapToDto(Comment comment) {
         return new CommentDTO(comment.getId(), comment.getBody());
     }
}