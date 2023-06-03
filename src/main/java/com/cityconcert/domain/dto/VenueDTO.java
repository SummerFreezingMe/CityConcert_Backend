package com.cityconcert.domain.dto;

import com.cityconcert.domain.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Venue} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO implements Serializable {

    private Long id;

    private String name;

    private String location;

    private String description;

    private Integer capacity;

    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenueDTO)) {
            return false;
        }

        VenueDTO venueDTO = (VenueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, venueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenueDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", location='" + getLocation() + "'" +
                ", description='" + getDescription() + "'" +
                ", capacity=" + getCapacity() +
                "}";
    }
}
