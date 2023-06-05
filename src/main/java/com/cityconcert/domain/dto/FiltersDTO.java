package com.cityconcert.domain.dto;

import com.cityconcert.domain.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Event} filters.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltersDTO {
    String genres;
    LocalDateTime date_first;
    LocalDateTime date_last;
    Double price_lowest;
    Double price_highest;


}
