package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.RequestType;
import com.cityconcert.domain.model.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link Request} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    Long id;

    Long userId;

    Long eventId;

    RequestType requestType;

    String description;

    String currentSeat;

    String wantedSeat;

    String seatFromUser;
}
