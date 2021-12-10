package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Long> {


    Owner findByContactNumber(String contactNumber);
}
