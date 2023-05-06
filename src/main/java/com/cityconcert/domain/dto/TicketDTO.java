package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.TicketStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.cityconcert.domain.Ticket} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TicketDTO implements Serializable {

    private Long id;

    private Double price;

    private String seat;

    private TicketStatus status;

    private LocalDateTime purchaseDate;


    private Long userId;

    private Long eventId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user) {
        this.userId = user;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketDTO)) {
            return false;
        }

        TicketDTO ticketDTO = (TicketDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ticketDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", seat='" + getSeat() + "'" +
            ", status='" + getStatus() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            ", userId=" + getUserId() +
            ", eventId=" + getEventId() +
            "}";
    }
}
