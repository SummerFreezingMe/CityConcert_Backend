package com.cityconcert.domain;

import com.cityconcert.domain.enumeration.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    Long id;

    Long userId;

    RequestType requestType;

    String description;

    String currentSeat;

    String wantedSeat;
}
