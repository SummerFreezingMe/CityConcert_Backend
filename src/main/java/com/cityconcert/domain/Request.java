package com.cityconcert.domain;

import com.cityconcert.domain.enumeration.RequestType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "request")
public class Request {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "request_type")
    RequestType requestType;
    @Column(name = "description")
    String description;
    @Column(name = "current_seat")
    String currentSeat;
    @Column(name = "wanted_seat")
    String wantedSeat;
}
