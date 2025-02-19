package com.domandre.repositories;

import com.domandre.dtos.QuoteDTO;
import com.domandre.entities.Author;
import com.domandre.entities.Quote;
import com.domandre.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByAuthor(Author author);

    List<QuoteDTO> findByUser(User user);

    @Query("SELECT q FROM Quote q " +
            "WHERE q.user = :user " +
            "AND (LOWER(q.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(q.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(q.author.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Quote> findByUserAndKeyword(@Param("user") User user, @Param("keyword") String keyword);
}
