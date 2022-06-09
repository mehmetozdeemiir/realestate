package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {


    Optional<City> findByCityName(String cityName);
    City findByCityNameAndDistrict(String cityName,String district);
}
