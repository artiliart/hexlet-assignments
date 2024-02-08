package exercise.controller;

import java.util.List;

import exercise.dto.*;
import exercise.service.AuthorService;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    private List<BookDTO> getAuthors() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    private BookDTO getAuthorById(@PathVariable Long id) {
        return bookService.getAuthor(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    private BookDTO createAuthor(@RequestBody BookCreateDTO request) {
        return bookService.createAuthor(request);
    }

    @PutMapping("/{id}")
    private BookDTO updateAuthor(@PathVariable Long id, @RequestBody BookUpdateDTO request) {
        return bookService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    private void deleteAuthor(@PathVariable Long id) {
        bookService.deleteAuthor(id);
    }
}
