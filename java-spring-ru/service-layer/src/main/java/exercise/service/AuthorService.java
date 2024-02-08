package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream().map(authorMapper::map).collect(Collectors.toList());
    }

    public AuthorDTO getAuthor(Long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("123")));
    }

    public AuthorDTO createAuthor(AuthorCreateDTO request) {
        return authorMapper.map(authorRepository.save(authorMapper.map(request)));
    }

    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO request) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("1234"));
        authorMapper.update(request,author);
        return authorMapper.map(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
