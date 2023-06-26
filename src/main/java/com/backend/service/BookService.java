package com.backend.service;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.backend.repository.BookRepository;
import com.backend.model.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  public List<Book> getList(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
    return bookRepository.findAllByUser(user);
  }


  public void create(Book book, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
    book.setUser(user);
    bookRepository.save(book);
  }


  public void update(Book book, Integer id) {
    Book bookExist = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));


    bookExist.setAuthor(book.getAuthor());
    bookExist.setName(book.getName());
    bookExist.setDescription(book.getDescription());
    bookRepository.save(bookExist);
  }

  public void delete(Integer id) {
    bookRepository.deleteById(id);
  }
}
