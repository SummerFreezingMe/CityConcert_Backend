package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.EventStatus;
import com.cityconcert.domain.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link Event} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDateTime startTime;

    private String ticketLimit;

    private String ticketPrice;

    private EventStatus status;

    private String genreDescriptors;

    private String image;

    private String description;

    private Long venue_id;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventDTO)) {
            return false;
        }

        EventDTO eventDTO = (EventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, eventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", ticketLimit=" + getTicketLimit() +
            ", status='" + getStatus() + "'" +
            ", venue=" + getVenue_id() +
            "}";
    }


}
