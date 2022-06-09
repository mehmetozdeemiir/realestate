package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.ForSaleEstateAgent;
import com.graduationproject.realestate.entities.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface ForSaleEstateAgentRepository extends JpaRepository<ForSaleEstateAgent,Long> {

    List<ForSaleEstateAgent> findByEstateAgentId(Long estateAgentId);

    List<ForSaleEstateAgent> findByCityId(Long cityId);

    List<ForSaleEstateAgent> findAllByCity_CityName(String cityName, Pageable pageable);

    List<ForSaleEstateAgent> findAllByPriceLessThanEqual(Long price,Pageable pageable);

    @Query("FROM ForSaleEstateAgent WHERE price< 300000 AND city.cityName=:cityName ")
    List<ForSaleEstateAgent> getAllCheapHouseByCityName(String cityName,Pageable pageable);

    List<ForSaleEstateAgent> findAllByListingDateLessThanEqual(LocalDate listingDate,Pageable pageable);

    List<ForSaleEstateAgent> findAllByProductType(ProductType productType, Pageable pageable);

    List<ForSaleEstateAgent> findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(Long price, String cityName, String district,Pageable pageable);

    List<ForSaleEstateAgent> findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(Long price,
                                                                                                                                            int buildingAge,
                                                                                                                                            Boolean balcony,
                                                                                                                                            Boolean furnished,
                                                                                                                                            String cityName,
                                                                                                                                            String district,
                                                                                                                                            Pageable pageable);

    /*
    Queryler
      @Query("SELECT u FROM ForSaleEstateAgent u JOIN u.city r WHERE r.cityName=:cityName")
      @Query("FROM ForSaleEstateAgent WHERE price < :price ")
      @Query("FROM ForSaleEstateAgent WHERE listingDate <= :listingDate")
      @Query("SELECT u FROM ForSaleEstateAgent u JOIN u.city r WHERE u.price<:price and r.cityName=:cityName and r.district=:district")
      @Query("SELECT u FROM ForSaleEstateAgent u JOIN u.city r  WHERE u.price<:price and u.buildingAge<:buildingAge and u.balcony=:balcony and u.furnished=:furnished and  r.cityName=:cityName and r.district=:district")
     */

}
