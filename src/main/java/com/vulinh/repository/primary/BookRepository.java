package com.vulinh.repository.primary;

import com.vulinh.model.primary.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PrimaryBaseRepository<String, Book> {
}