package com.cityconcert.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest extends AbstractRequest {

    String currentSeat;

    String wantedSeat;
}
