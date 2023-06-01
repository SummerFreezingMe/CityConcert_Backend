package com.cityconcert.repository;

import com.cityconcert.domain.model.Request;
import com.cityconcert.domain.enumeration.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestsByRequestType(RequestType requestType);
}
