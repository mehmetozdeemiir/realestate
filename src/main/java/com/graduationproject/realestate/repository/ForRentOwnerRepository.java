package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForRentOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForRentOwnerRepository extends JpaRepository<ForRentOwner,Long> {
    List<ForRentOwner> findByCityId(Long cityId);

    List<ForRentOwner>findByOwnerId(Long ownerId);
}
