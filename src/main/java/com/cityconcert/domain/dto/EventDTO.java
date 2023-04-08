package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.EventStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.cityconcert.domain.Event} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EventDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDateTime startTime;

    private Integer ticketLimit;

    private EventStatus status;
    private String genreDescriptors;


    private String description;
    private Long venue_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(Integer ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Long getVenue() {
        return venue_id;
    }

    public void setVenue(Long venue) {
        this.venue_id = venue;
    }

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
            ", venue=" + getVenue() +
            "}";
    }

    public String getGenreDescriptors() {
        return genreDescriptors;
    }

    public void setGenreDescriptors(String genreDescriptors) {
        this.genreDescriptors = genreDescriptors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
