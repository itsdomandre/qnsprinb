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
@RequestMapping("/api/authors")

public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequest request) {
        Author savedAuthor = authorService.addAuthor(request);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request) {
        Author updatedAuthor = authorService.updateAuthor(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor (@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
