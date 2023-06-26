package com.backend.controller;
import com.backend.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.model.Book;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor()
public class BookController {

  private final BookService bookService;

  @GetMapping("/list")
  public ResponseEntity<List<Book>> list (
          HttpServletRequest request
  )  {
    String user = (String) request.getAttribute("user");
    List<Book> books = bookService.getList(user);
    return ResponseEntity.ok(books);
  }

  @PostMapping("")
  public ResponseEntity<String> create (@RequestBody Book book, HttpServletRequest request) {
    String user = (String) request.getAttribute("user");

    bookService.create(book, user);
    return ResponseEntity.ok("Book created");
  }


  @PutMapping("/{id}")
  public ResponseEntity<String> update (@RequestBody Book book,
                                        HttpServletRequest request,
                                        @PathVariable Integer id) {

    bookService.update(book, id);
    return ResponseEntity.ok("Book updated");
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete (HttpServletRequest request,
                                        @PathVariable Integer id) {
    bookService.delete(id);
    return ResponseEntity.ok("Book deleted");
  }


}
