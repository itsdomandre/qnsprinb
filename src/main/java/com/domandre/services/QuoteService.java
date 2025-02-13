package com.domandre.services;

import com.domandre.dtos.QuoteDTO;
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

    public QuoteDTO addQuote(Quote quote, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        quote.setAuthor(author);
        Quote savedQuote = quoteRepository.save(quote);

        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setId(savedQuote.getId());
        quoteDTO.setTitle(savedQuote.getTitle());
        quoteDTO.setContent(savedQuote.getContent());
        quoteDTO.setSection(savedQuote.getSection());
        quoteDTO.setAuthorId(savedQuote.getAuthor().getId());

        return quoteDTO;
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
