package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    private List<AuthorDTO> getAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    private AuthorDTO getAuthorById(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    private AuthorDTO createAuthor(@RequestBody AuthorCreateDTO request) {
        return authorService.createAuthor(request);
    }

    @PutMapping("/{id}")
    private AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdateDTO request) {
        return authorService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    private void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
