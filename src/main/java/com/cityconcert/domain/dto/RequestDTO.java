package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    Long userId;

    Long eventId;

    RequestType requestType;

    String description;

    String currentSeat;

    String wantedSeat;

    String seatFromUser;
}
