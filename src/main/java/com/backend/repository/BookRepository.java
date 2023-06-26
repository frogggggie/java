package com.backend.repository;

import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List<Book> findAllByUser(User user);

}
