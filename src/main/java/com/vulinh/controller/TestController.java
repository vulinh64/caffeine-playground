package com.vulinh.controller;

import com.vulinh.repository.primary.BookRepository;
import com.vulinh.repository.secondary.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final BookRepository bookRepository;
  private final EmployeeRepository employeeRepository;

  @GetMapping("/employee/id/{id}")
  public Object getEmployee(@PathVariable("id") String id) {
    return employeeRepository.findById(
        id, () -> new UnsupportedOperationException("Not supported"));
  }

  @GetMapping("/employee/all")
  public Object allEmployees(Pageable pageable) {
    return employeeRepository.findAll(pageable);
  }

  @GetMapping("/book/id/{id}")
  public Object getBook(@PathVariable("id") String id) {
    return bookRepository.findById(id, () -> new UnsupportedOperationException("Not supported"));
  }

  @GetMapping("/book/all")
  public Object allBooks(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }
}
