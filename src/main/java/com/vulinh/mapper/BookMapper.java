package com.vulinh.mapper;

import com.vulinh.dto.BookDTO;
import com.vulinh.model.primary.Book;
import com.vulinh.template.AbstractMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true))
public interface BookMapper extends AbstractMapper<Book, BookDTO> {}
