package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/for-rent-estate-agents")
@RequiredArgsConstructor
public class ForRentEstateAgentController {
    private final ForRentEstateAgentService forRentEstateAgentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentEstateAgentResponse addEstateAgentRent(@Valid @RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        log.info("addEstateAgentRent method started");
        log.info("added:{} ",forRentEstateAgentRequest);
        return forRentEstateAgentService.addEstateAgentRent(forRentEstateAgentRequest);
    }

    @PutMapping("/{id}")
    public ForRentEstateAgentResponse updateEstateAgentRent(@PathVariable Long id, @Valid @RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        log.info("updateEstateAgentRent method started");
        log.info("updated:{} {} ",id,forRentEstateAgentRequest);
        return forRentEstateAgentService.updateEstateAgentRent(id,forRentEstateAgentRequest);
    }

    @GetMapping
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        log.info("getAllForRentEstateAgentWithParam method started");
        return forRentEstateAgentService.getAllForRentEstateAgentWithParam(estateAgentId);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable Long id){
        log.info("deleteRent method started");
        forRentEstateAgentService.deleteRent(id);
        log.info("deleted: {} ",id);
    }

    @GetMapping("/price/{no}/{size}")
    public List<ForRentEstateAgentResponse> getAllByPrice(@RequestParam Long price, @PathVariable int no, @PathVariable int size ){
        log.info("getAllByPrice method started");
        log.info("Requested values: price: {}",price);
        return forRentEstateAgentService.getAllByPrice(price,no,size);
    }

    @GetMapping("/product-types")
    public List<ForRentEstateAgentResponse> getAllByProductType(@RequestParam ProductType productType, Pageable pageable){
        log.info("getAllByImmovableTypes method started");
        log.info("Requested values: Product Type: {} ", productType);
        return forRentEstateAgentService.findAllByProductType(productType,pageable);
    }

    @GetMapping("/listing-date")
    public List<ForRentEstateAgentResponse> getAllByListingDate(@RequestParam LocalDate listingDate, Pageable pageable) {
        log.info("getAllByListingDate method started");
        log.info("Requested values: Listing date: {}",listingDate);
        return forRentEstateAgentService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/city-district-price")
    public List<ForRentEstateAgentResponse> findByCityNameAndDistrictAndPrice(@RequestParam Long price,@RequestParam String cityName, @RequestParam String district,  Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice method started");
        log.info("Requested values: City Name: {} , District Name: {}, Price: {}",price,cityName,district);
        return forRentEstateAgentService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/big-filter")
    public List<ForRentEstateAgentResponse> bigFilter(@RequestParam Long price, @RequestParam int buildingAge, @RequestParam Boolean balcony, @RequestParam Boolean furnished, @RequestParam String cityName, @RequestParam String district, Pageable pageable) {
        log.info("bigFilter method started");
        log.info("Requested values: Price:{}, Building Age : {}, Balcony: {},Furnished: {},City Name: {} ,District Name: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forRentEstateAgentService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }


}
