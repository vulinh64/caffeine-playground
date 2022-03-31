package com.vulinh.dto;

import com.vulinh.template.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookDTO extends AbstractDTO {

    private static final long serialVersionUID = 4523619152452345718L;

    private String id;
    private String name;
    private String author;
    private LocalDate publishedDate;

}