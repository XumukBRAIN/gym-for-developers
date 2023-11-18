package com.dev.gdstoreservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GdBookDto {
    private Integer id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer pages;
    private LocalDate creationDate;
    private String topic;
}
