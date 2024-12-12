package com.domandre.services;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.repositories.AuthorRepository;
import com.domandre.repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public List<Quote> getAll (Quote request){
        return quoteRepository.findAll();
    }

    public Quote addQuote(Quote request){
//        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow();
        Quote quote = new Quote();
        quote.setTitle(request.getTitle());
        quote.setContent(request.getContent());
        quote.setSection(request.getSection());

//        quote.setAuthorId(Set.of(request.getAuthorId()));
//        author.setQuote(quote);

        return quoteRepository.save(quote);
    }

}
