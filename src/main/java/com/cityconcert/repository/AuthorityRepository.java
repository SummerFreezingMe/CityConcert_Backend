package com.cityconcert.repository;

import com.cityconcert.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Role} entity.
 */
public interface AuthorityRepository extends JpaRepository<Role, String> {}
