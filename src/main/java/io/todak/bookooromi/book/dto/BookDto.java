package io.todak.bookooromi.book.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class BookDto {

    public static class Request {

        @Getter
        @Setter
        public static class Create {
            @NotEmpty
            private String isbn; //ISBN
            @NotEmpty
            private String title; // 제목
            @NotEmpty
            private String author; // 저자
            @NotEmpty
            private String description; // 설명
            @NotEmpty
            private String publisher; //출판사
            @NotEmpty
            private String category; // 카테고리
        }

    }

    public static class Response {
        @Getter
        @AllArgsConstructor
        public static class Create {
            private String isbn;
            private String title;
            private String author;
            private String description;
            private String publisher;
            private String category;
        }
    }

}
