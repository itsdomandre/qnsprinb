package com.domandre.controllers;

import com.domandre.DTOs.QuoteDTO;
import com.domandre.entities.Quote;
import com.domandre.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")

public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("")
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
