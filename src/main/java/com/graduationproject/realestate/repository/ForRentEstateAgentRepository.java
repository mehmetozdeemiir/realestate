package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForRentEstateAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForRentEstateAgentRepository extends JpaRepository<ForRentEstateAgent,Long> {
    List<ForRentEstateAgent> findByCityId(Long cityId);

    List<ForRentEstateAgent> findByEstateAgentId(Long estateAgentId);
}
