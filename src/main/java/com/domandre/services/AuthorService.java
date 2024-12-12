package com.domandre.services;

import com.domandre.controllers.request.AuthorRequest;
import com.domandre.entities.Author;
import com.domandre.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
