package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForSaleRepository extends JpaRepository<ForSale,Long> {
    List<ForSale> findByOwnerId(Long ownerId);

    List<ForSale> findByEstateAgentId(Long estateAgentId);

    List<ForSale> findByCityId(Long cityId);
}
