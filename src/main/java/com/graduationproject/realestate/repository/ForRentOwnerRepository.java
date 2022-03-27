package com.graduationproject.realestate.repository;
import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ForRentOwnerRepository extends JpaRepository<ForRentOwner,Long> {

    List<ForRentOwner> findByCityId(Long cityId);

    List<ForRentOwner>findByOwnerId(Long ownerId);

    List<ForRentOwner> findAllByCity_CityName(String cityName, Pageable pageable);

    List<ForRentOwner> findAllByPriceLessThanEqual(Long price, Pageable pageable);

    List<ForRentOwner> findAllByListingDateLessThanEqual(LocalDate listingDate, Pageable pageable);

    List<ForRentOwner> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes, Pageable pageable);

    List<ForRentOwner> findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(Long price,String cityName, String district, Pageable pageable);

    List<ForRentOwner> findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(Long price,int buildingAge,Boolean balcony,Boolean furnished,String cityName,String district, Pageable pageable);


}
