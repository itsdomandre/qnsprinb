package com.domandre.services;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.dtos.QuoteDTO;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.entities.User;
import com.domandre.repositories.AuthorRepository;
import com.domandre.repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;
    private final UserService userService;


    public List<QuoteDTO> getAll() {
        List<Quote> quotes = quoteRepository.findAll();
        return quotes.stream()
                .map(QuoteDTO::new)
                .collect(Collectors.toList());
    }

    public QuoteDTO addQuote(QuoteRequest request) {
        User user = userService.getCurrentUser();
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Quote quote = Quote.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .section(request.getSection())
                .author(author)
                .user(user)
                .build();

        Quote savedQuote = quoteRepository.save(quote);
        return new QuoteDTO(savedQuote);
    }

    public List<QuoteDTO> getMyQuotes() {
        User currentUser = userService.getCurrentUser();
        return quoteRepository.findByUser(currentUser);
    }

    public List<Quote> getQuotesByAuthor(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return quoteRepository.findByAuthor(author);
        } else {
            throw new IllegalArgumentException("Author with ID " + authorId + " not found.");
        }
    }

    public Quote updateQuote(Long id, QuoteRequest request) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote Id not found"));

        if (request.getTitle() != null) quote.setTitle(request.getTitle());
        if (request.getContent() != null) quote.setContent(request.getContent());
        if (request.getSection() != null) quote.setSection(request.getSection());
        if (request.getAuthorId() != null) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            quote.setAuthor(author);
        }
        return quoteRepository.save(quote);
    }

    public void deleteQuote(Long id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
        }
    }

    public List<QuoteDTO> quotesByUserAndKeyword(String keyword) {
        User user = userService.getCurrentUser();
        List<Quote> quotes = quoteRepository.findByUserAndKeyword(user, keyword);
        return quotes.stream()
                .map(QuoteDTO::new)
                .collect(Collectors.toList());
    }
}
