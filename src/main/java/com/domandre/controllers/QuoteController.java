package com.domandre.controllers;

import com.domandre.dtos.QuoteDTO;
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

    @PostMapping("/add/{authorId}")
    public ResponseEntity<QuoteDTO> addQuote (@RequestBody Quote quote, @PathVariable Long authorId) {
        QuoteDTO savedQuoteDTO = quoteService.addQuote(quote, authorId);
        return ResponseEntity.ok(savedQuoteDTO);
    }
}
