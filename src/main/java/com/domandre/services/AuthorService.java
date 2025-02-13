package com.domandre.services;

import com.domandre.controllers.request.AuthorRequest;
import com.domandre.entities.Author;
import com.domandre.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthorService {
    private final AuthorRepository repository;

    public List<Author> getAll() {
        return repository.findAll();
    }
    public Author addAuthor(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setDescription(request.getDescription());
        return repository.save(author);
    }
    public Author getAuthorById(Long authorId){
        return repository.findById(authorId).orElse(null);
    }
    public Author updateAuthor(Long id, AuthorRequest request) {
        Author author = repository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getDescription() != null) {
            author.setDescription(request.getDescription());
        }
        return repository.save(author);
    }
    public void deleteAuthor(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
