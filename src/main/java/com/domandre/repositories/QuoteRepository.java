package com.domandre.repositories;

import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByAuthor(Author author);
}
