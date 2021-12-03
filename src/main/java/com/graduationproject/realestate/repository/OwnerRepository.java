package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
