package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::map).collect(Collectors.toList());
    }

    public BookDTO getAuthor(Long id) {
        return bookMapper.map(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("123")));
    }

    public BookDTO createAuthor(BookCreateDTO request) {
        return bookMapper.map(bookRepository.save(bookMapper.map(request)));
    }

    public BookDTO updateAuthor(Long id, BookUpdateDTO request) {
        var author = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("1234"));
        bookMapper.update(request,author);
        return bookMapper.map(bookRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        bookRepository.deleteById(id);
    }
}
