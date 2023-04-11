package com.cityconcert.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;


private Long id;
    private String username;

    private String email;
    private String password;

    private String passwordConfirm;

    private String role;
    private String imageUrl;
    // prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            ", login='" + username + '\'' +
                ", password='" + password + '\'' +
            "}";
    }


}
