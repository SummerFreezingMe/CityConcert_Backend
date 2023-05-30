package com.cityconcert.domain.dto;

import com.cityconcert.domain.enumeration.TicketStatus;
import com.cityconcert.domain.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link Ticket} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO implements Serializable {

    private Long id;

    private Double price;

    private String seat;

    private TicketStatus status;

    private LocalDateTime purchaseDate;


    private Long userId;

    private Long eventId;


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
