package com.domandre.controllers;

import com.domandre.controllers.request.AuthorRequest;
import com.domandre.entities.Author;
import com.domandre.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping("/authors/save")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequest addCardRequest) {
        Author savedAuthor = authorService.addAuthor(addCardRequest);
        return ResponseEntity.ok(savedAuthor);
    }

}
