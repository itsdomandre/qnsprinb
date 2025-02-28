package com.domandre.dtos;

import com.domandre.entities.Quote;
import com.domandre.enums.Section;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuoteDTO {
    // private Long id;
    private String title;
    private String content;
    private Section section;

    // TODO: Refatorar
    public QuoteDTO(Quote quote) {
    //    this.id = quote.getId();
        this.title = quote.getTitle();
        this.content = quote.getContent();
        this.section = quote.getSection();
    }
}