package com.domandre.controllers;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.entities.Quote;
import com.domandre.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")

public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Quote>> getQuotes(){
        List<Quote> quotes = quoteService.getAll();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Quote>> getQuotesByAuthor (@PathVariable Long authorId){
        List<Quote> quotes = quoteService.getQuotesByAuthor(authorId);
        return ResponseEntity.ok(quotes);
    }

    @PostMapping("/create/{authorId}")
    public ResponseEntity<Quote> addQuote (@RequestBody QuoteRequest request, @PathVariable Long authorId) {
        Quote savedQuote = quoteService.addQuote(request, authorId);
        return ResponseEntity.ok(savedQuote);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Quote> updateQuote (@PathVariable Long id, @RequestBody QuoteRequest request){
        Quote updatedQuote = quoteService.updateQuote(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuote (@PathVariable Long id){
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
