package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForSaleEstateAgent;
import com.graduationproject.realestate.entities.ForSaleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForSaleEstateAgentRepository extends JpaRepository<ForSaleEstateAgent,Long> {
    List<ForSaleEstateAgent> findByEstateAgentId(Long estateAgentId);
}
