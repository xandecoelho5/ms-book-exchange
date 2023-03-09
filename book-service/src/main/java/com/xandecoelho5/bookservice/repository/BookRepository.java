package com.xandecoelho5.bookservice.repository;

import com.xandecoelho5.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
