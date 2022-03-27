package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface ForSaleOwnerRepository extends JpaRepository<ForSaleOwner,Long> {

    List<ForSaleOwner> findByOwnerId(Long ownerId);

    List<ForSaleOwner> findByCityId(Long cityId);

    @Query("FROM ForSaleOwner WHERE price <300000")
    List<ForSaleOwner> under300thousand(Pageable pageable);

    @Query("FROM ForSaleOwner WHERE price< 300000 AND city.cityName=:cityName")
    List<ForSaleOwner> getAllCheapHouseByCityName(String cityName);

    List<ForSaleOwner> findAllByCity_CityName(String cityName , Pageable pageable);

    List<ForSaleOwner> findAllByPriceLessThanEqual(Long price,Pageable pageable);

    List<ForSaleOwner> findAllByListingDateLessThanEqual(LocalDate listingDate, Pageable pageable);

    List<ForSaleOwner> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes,Pageable pageable);

    List<ForSaleOwner> findAllByPriceLessThanEqualAndCity_CityName(Long price, String cityName , Pageable pageable);

    List<ForSaleOwner> findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(Long price,String cityName, String district,Pageable pageable);

    List<ForSaleOwner> findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(Long price,int buildingAge,Boolean balcony,Boolean furnished,String cityName,String district,Pageable pageable);


      /*
       Queryler
       @Query("SELECT u FROM ForSaleOwner u JOIN u.city r WHERE r.cityName=:cityName")
       @Query("FROM ForSaleOwner WHERE price <=:price")
       @Query("FROM ForSaleOwner WHERE listingDate <= :listingDate")
       @Query("FROM ForSaleOwner where price<=:price AND city.cityName=:cityName")
       @Query("SELECT u FROM ForSaleOwner u JOIN u.city r WHERE u.price<=:price and r.cityName=:cityName and r.district=:district")
       @Query("SELECT u FROM ForSaleOwner u JOIN u.city r  WHERE u.price<:price and u.buildingAge<:buildingAge and u.balcony=:balcony and u.furnished=:furnished and  r.cityName=:cityName and r.district=:district")

      */
}
