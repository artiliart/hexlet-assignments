package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;


// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer id) {
       var userPosts = posts.stream().filter( i -> i.getUserId() == id).toList();
       return ResponseEntity.ok()
               .body(userPosts);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable Integer id, @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

}
// END
