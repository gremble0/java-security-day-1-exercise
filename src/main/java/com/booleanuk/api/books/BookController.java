package com.booleanuk.api.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
  private final BookRepository repository;

  public BookController(BookRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public ResponseEntity<Book> post(@RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(book));
  }

  @GetMapping
  public ResponseEntity<List<Book>> get() {
    return ResponseEntity.ok(this.repository.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Book> get(@PathVariable int id) throws ResponseStatusException{
    return ResponseEntity.ok(this.repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }
}
