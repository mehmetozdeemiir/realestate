package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForRentEstateAgent;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ForRentEstateAgentRepository extends JpaRepository<ForRentEstateAgent,Long> {

    List<ForRentEstateAgent> findByEstateAgentId(Long estateAgentId);

    List<ForRentEstateAgent> findByCityId(Long cityId);

    List<ForRentEstateAgent> findAllByCity_CityName(String cityName, Pageable pageable);

    List<ForRentEstateAgent> findAllByPriceLessThanEqual(Long price,Pageable pageable);

    List<ForRentEstateAgent> findAllByListingDateLessThanEqual(LocalDate listingDate,Pageable pageable);

    List<ForRentEstateAgent> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes,Pageable pageable);//calısmazsa string olarak yazılacak

    List<ForRentEstateAgent> findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(Long price,String cityName, String district, Pageable pageable);

    List<ForRentEstateAgent> findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district, Pageable pageable);

}
