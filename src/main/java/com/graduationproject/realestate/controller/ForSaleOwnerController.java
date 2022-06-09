package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.request.BigFilterRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/for-sale-owners")
@RequiredArgsConstructor
public class ForSaleOwnerController {

    private final ForSaleOwnerService forSaleOwnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleOwnerResponse addOwnerSale(@Valid @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        log.info("addOwnerSale metodu başladı");
        log.info("Sahibinden kişisi satılık ev added: {} ",forSaleOwnerRequest);
        return forSaleOwnerService.addOwnerSale(forSaleOwnerRequest);
    }

    @PutMapping("/{id}")
    public ForSaleOwnerResponse updateOwnerSale(@PathVariable Long id,@Valid @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        log.info("updateOwnerSale method started");
        log.info("updated: {} {}",id,forSaleOwnerRequest);
        return forSaleOwnerService.updateOwnerSale(id,forSaleOwnerRequest);
    }

    @GetMapping("/300-thousand-under")
    public List<ForSaleOwnerResponse> getAllUnder300Thousand(Pageable pageable){
        log.info("getAllUnder300Thousand method started");
        return forSaleOwnerService.under300thousand(pageable);
    }

    @GetMapping("/price")
    public List<ForSaleOwnerResponse> findAllByPrice(@RequestParam Long price,Pageable pageable) {
        log.info("getAllForSalePrice method started");
        log.info("Requested values: Price: {} ",price);
        return forSaleOwnerService.findAllByPrice(price,pageable);
    }

    @GetMapping("/city-name")
    public List<ForSaleOwnerResponse> findByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("findByCityName method started");
        log.info("Requested values: City Name: {} ",cityName);
        return forSaleOwnerService.findByCityName(cityName,pageable);
    }

    @GetMapping("/cheap-house")
    public List<ForSaleOwnerResponse> getAllCheapHouseByCityName(@RequestParam String cityName){
        log.info("getAllCheapHouseByCityName method started");
        log.info("Requested values: City Name: {} ",cityName);
        return forSaleOwnerService.getAllCheapHouseByCityName(cityName);
    }

    @GetMapping("/immovables-types")
    public List<ForSaleOwnerResponse> getAllByProductType(@RequestParam ProductType productType, Pageable pageable){
        log.info("getAllByImmovableTypes method started");
        log.info("Requested values: Product Type {} ", productType);
        return forSaleOwnerService.findAllByProductType(productType,pageable);
    }

    @GetMapping("/listing-date")
    public List<ForSaleOwnerResponse> getAllByListingDate(@RequestParam LocalDate listingDate,Pageable pageable) {
        log.info("getAllByListingDate method started");
        log.info("Requested values: Listing Date: {} ",listingDate);
        return forSaleOwnerService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/city-price")
    public List<ForSaleOwnerResponse> findByCityNameAndPrice(@RequestParam Long price,@RequestParam String cityName,  Pageable pageable){
        log.info("findByCityNameAndPrice method started");
        log.info("Requested values: City Name: {} , Price: {}",cityName,price);
        return forSaleOwnerService.findByCityNameAndPrice(price,cityName,pageable);
    }

    @GetMapping("/city-district-price")
    public List<ForSaleOwnerResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price,Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice method started");
        log.info("Requested values: City Name: {} , District Name: {}, Price: {}",cityName,district,price);
        return forSaleOwnerService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/big-filter")
    public List<ForSaleOwnerResponse> bigFilter(@RequestParam Long price,@RequestParam int buildingAge,@RequestParam Boolean balcony,@RequestParam Boolean furnished,@RequestParam String cityName,@RequestParam String district,Pageable pageable){
        log.info("bigFilter method started");
        log.info("Requested values: Price:{}, Building Age : {}, Balcony: {},Furnished: {},City Name: {} , District Name: {}",price,buildingAge,balcony,furnished,cityName,district);
        BigFilterRequest request = BigFilterRequest.builder()
                .price(price)
                .buildingAge(buildingAge)
                .balcony(balcony)
                .furnished(furnished)
                .cityName(cityName)
                .district(district)
                .build();
        return forSaleOwnerService.bigFilter(request,pageable);
        //localhost:8888/api/for-sale-owner-big-filter?price=291000&buildingAge=5&balcony=true&furnished=false&cityName=Ankara&district=Bahçelievler
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id){
        log.info("deleteSale method started");
        forSaleOwnerService.deleteSaleOwner(id);
        log.info("deleted: {} ",id);
    }
}
