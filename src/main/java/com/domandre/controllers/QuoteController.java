package com.domandre.controllers;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class QuoteController {
    private final QuoteService quoteService;
    @GetMapping("/quotes")
    public ResponseEntity<?> getQuotes(){
        return null;
    }

    @PostMapping("/quotes/save")
    public ResponseEntity<Quote> addAuthor(@RequestBody Quote addQuoteRequest) {
        Quote savedQuote = quoteService.addQuote(addQuoteRequest);
        return ResponseEntity.ok(savedQuote);
    }
}
