package com.domandre.services;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.repositories.AuthorRepository;
import com.domandre.repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public List<Quote> getAll() {
        return quoteRepository.findAll();
    }

    public QuoteRequest addQuote(Quote quote, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        quote.setAuthor(author);
        Quote savedQuote = quoteRepository.save(quote);

        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setId(savedQuote.getId());
        quoteRequest.setTitle(savedQuote.getTitle());
        quoteRequest.setContent(savedQuote.getContent());
        quoteRequest.setSection(savedQuote.getSection());
        quoteRequest.setAuthorId(savedQuote.getAuthor().getId());

        return quoteRequest;
    }

    public List<Quote> getQuotesByAuthor (Long authorId){
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return quoteRepository.findByAuthor(author);
        }else {
            throw new IllegalArgumentException("Author with ID " + authorId + " not found.");
        }
    }
}
