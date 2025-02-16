package com.domandre.entities;

import com.domandre.entities.enums.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "quotes")
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}