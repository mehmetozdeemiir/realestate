package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentOwnerService;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/api/for-rent-owners")
@RequiredArgsConstructor
@Slf4j
public class ForRentOwnerController {

    private final ForRentOwnerService forRentOwnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentOwnerResponse addOwnerRent(@Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        log.info("addOwnerRent method started");
        log.info("added: {}",forRentOwnerRequest);
        return forRentOwnerService.addOwnerRent(forRentOwnerRequest);
    }

    @PutMapping("/{id}")
    public ForRentOwnerResponse updateOwnerRent(@PathVariable Long id, @Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        log.info("updateOwnerRent method started");
        log.info("updated: {} {} ",id,forRentOwnerRequest);
        return forRentOwnerService.updateOwnerRent(id,forRentOwnerRequest);
    }

    @GetMapping
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(@RequestParam(required = false) Long ownerId) {
        log.info("getAllForRentOwnerWithParam method started");
        return forRentOwnerService.getAllForRentOwnerWithParam(Optional.ofNullable(ownerId));
    }

    @GetMapping("/city-name")
    public List<ForRentOwnerResponse> getAllForRentOwnerByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("getAllForRentOwnerByCityName method started");
        log.info("Requested values: City Name: {} ",cityName);
        return forRentOwnerService.getAllByCityName(cityName,pageable);
    }

    @GetMapping("/price")
    public List<ForRentOwnerResponse> getAllByPrice(@RequestParam Long price, Pageable pageable){
        log.info("getAllByPrice method started");
        log.info("Requested values: Price: {}",price);
        return forRentOwnerService.getAllByPrice(price,pageable);
    }

    @GetMapping("/listing-date") ///api/for-rent-owner-listing-date?listingDate=22.12.2021
    public List<ForRentOwnerResponse> getAllByListingDate(@RequestParam LocalDate listingDate, Pageable pageable){
        log.info("getAllByListingDate method started");
        log.info("Requested values: Listing Date: {}",listingDate);
        return forRentOwnerService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/immovables-types")
    public List<ForRentOwnerResponse> getAllByProductType(@RequestParam ProductType productType, Pageable pageable){
        log.info("getAllByImmovableTypes method started");
        log.info("Requested values: Product Type : {}", productType);
        return forRentOwnerService.findAllByProductType(productType,pageable);
    }

    @GetMapping("/city-district-price")
    public List<ForRentOwnerResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price, Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice method started");
        log.info("Requested values: Price : {},City Name: {} ,District Name: {}",price,cityName,district);
        return forRentOwnerService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/big-filter")
    public List<ForRentOwnerResponse> bigFilter(@RequestParam Long price,@RequestParam int buildingAge,@RequestParam Boolean balcony,@RequestParam Boolean furnished,@RequestParam String cityName,@RequestParam String district, Pageable pageable) {
        log.info("bigFilter method started");
        log.info("Requested values: Price: {}, Building Age : {}, Balcony: {},Furnished: {},City Name: {} ,District Name: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forRentOwnerService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable Long id){
        log.info("deleteRent method started");
        forRentOwnerService.deleteRent(id);
        log.info("deleted : {}",id);
    }
}