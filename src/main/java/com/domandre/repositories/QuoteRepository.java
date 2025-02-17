package com.domandre.repositories;

import com.domandre.dtos.QuoteDTO;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByAuthor(Author author);

    List<QuoteDTO> findByUser(User user);
}
