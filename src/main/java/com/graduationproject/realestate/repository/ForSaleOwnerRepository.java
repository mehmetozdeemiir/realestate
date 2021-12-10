package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForSaleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForSaleOwnerRepository extends JpaRepository<ForSaleOwner,Long> {
    List<ForSaleOwner> findByOwnerId(Long ownerId);
}
