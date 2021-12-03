package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForRentRepository extends JpaRepository<ForRent,Long> {
    List<ForRent> findByOwnerId(Long ownerId);
    List<ForRent> findByEstateAgentId(Long estateAgentId);
    List<ForRent> findByCityId(Long cityId);
}
