package com.domandre.mappers;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.entities.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapper {
    public QuoteRequest toDTO (Quote entity){
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setId(entity.getId());
        quoteRequest.setTitle(entity.getTitle());
        quoteRequest.setContent(entity.getContent());
        quoteRequest.setSection(entity.getSection());
        quoteRequest.setAuthorId(entity.getAuthor().getId());

        return quoteRequest;
    }
}
