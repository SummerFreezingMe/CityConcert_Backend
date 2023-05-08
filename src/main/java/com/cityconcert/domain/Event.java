package com.cityconcert.domain;

import com.cityconcert.domain.enumeration.EventStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "ticket_limit")
    private Integer ticketLimit;

    @Column(name = "genre_descriptors")
    private String genreDescriptors;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;

    @Column(name = "venue_id")
    private Long venueId;

    @Column(name = "image")
    private byte[] image;

    public Long getId() {
        return this.id;
    }

    public Event id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return this.name;
    }

    public Event name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getTicketLimit() {
        return this.ticketLimit;
    }

    public void setTicketLimit(Integer ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public EventStatus getStatus() {
        return this.status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public void setVenueId(Long venue) {
        this.venueId = venue;
    }

    public Event venueId(Long venue) {
        this.setVenueId(venue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        return id != null && id.equals(((Event) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", venueId=" + getVenueId() +
            ", name='" + getName() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", ticketLimit=" + getTicketLimit() +
            ", status='" + getStatus() + "'" +
            "}";
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenreDescriptors() {
        return genreDescriptors;
    }

    public void setGenreDescriptors(String genreDescriptors) {
        this.genreDescriptors = genreDescriptors;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
