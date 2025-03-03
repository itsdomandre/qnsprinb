package com.domandre.dtos;

import com.domandre.entities.Quote;
import com.domandre.enums.Section;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuoteDTO {
    private String title;
    private String content;
    private Section section;

    // TODO: Refatorar
    public QuoteDTO(Quote quote) {
        this.title = quote.getTitle();
        this.content = quote.getContent();
        this.section = quote.getSection();
    }
}