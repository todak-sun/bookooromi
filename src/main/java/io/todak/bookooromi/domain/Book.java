package io.todak.bookooromi.domain;

import io.todak.bookooromi.common.entities.BaseDateTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "tb_book")
@Entity
public class Book extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    private Long id;

    @Column(unique = true, name = "isbn")
    private String isbn; //ISBN

    @Column(name = "title")
    private String title; // 제목

    @Column(name = "author")
    private String author; // 저자

    @Column(name = "description")
    private String description; // 설명

    @Column(name = "publisher")
    private String publisher; //출판사

    @Column(name = "category")
    private String category; // 카테고리

    @Builder
    public Book(Long id, String isbn, String title, String author, String description, String publisher, String category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.category = category;
    }
}
