package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {

    City findByCityName(String cityName);
    City findByCityNameAndDistrict(String cityName,String district);
}
