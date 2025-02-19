package com.domandre.controllers;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.dtos.QuoteDTO;
import com.domandre.entities.Quote;
import com.domandre.services.QuoteService;
import com.domandre.services.UserService;
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
    private final UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuoteDTO>> getQuotes() {
        List<QuoteDTO> quotes = quoteService.getAll();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(@PathVariable Long authorId) {
        List<Quote> quotes = quoteService.getQuotesByAuthor(authorId);
        return ResponseEntity.ok(quotes);
    }

    @PostMapping("/create")
    public ResponseEntity<QuoteDTO> addQuote(@RequestBody QuoteRequest request) {
        QuoteDTO savedQuote = quoteService.addQuote(request);
        return ResponseEntity.ok(savedQuote);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody QuoteRequest request) {
        Quote updatedQuote = quoteService.updateQuote(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-quotes")
    public ResponseEntity<List<QuoteDTO>> myQuotes() {
        List<QuoteDTO> quotes = quoteService.getMyQuotes();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/my-quotes/search")
    public ResponseEntity<List<QuoteDTO>> searchQuotes(@RequestParam String keyword) {
        List<QuoteDTO> quotes = quoteService.quotesByUserAndKeyword(keyword);
        return ResponseEntity.ok(quotes);
    }
}
