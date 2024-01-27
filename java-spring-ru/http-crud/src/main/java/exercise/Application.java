package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> allPosts() {
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> postById(@PathVariable String id) {
        return posts.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable String id) {
        var findPost = posts.stream().filter(i -> i.getId().equals(id)).findFirst();
        if (findPost.isPresent()) {
            var getPost = findPost.get();
            getPost.setBody(post.getBody());
            getPost.setTitle(post.getTitle());
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        var postFind = posts.stream().filter(i -> i.getId().equals(id)).findFirst();
        postFind.ifPresent(post -> posts.remove(post));
    }
    // END
}
