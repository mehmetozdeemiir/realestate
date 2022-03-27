package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentOwnerService;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import com.graduationproject.realestate.response.ListByCityResponseO;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForRentOwnerController {

    private final ForRentOwnerService forRentOwnerService;

    @PostMapping("/for-rent-owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentOwnerResponse addOwnerRent(@Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        log.info("addOwnerRent metodu başladı");
        log.info("Sahibinden kişisi kiralık konut ekledi: {}",forRentOwnerRequest);
        return forRentOwnerService.addOwnerRent(forRentOwnerRequest);
    }

    @PutMapping("/for-rent-owners/{id}")
    public ForRentOwnerResponse updateOwnerRent(@PathVariable Long id, @Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        log.info("updateOwnerRent metodu başladı");
        log.info("Sahibinden kişisi konut güncelledi: {} {} ",id,forRentOwnerRequest);
        return forRentOwnerService.updateOwnerRent(id,forRentOwnerRequest);
    }

    @GetMapping("/for-rent-owners")
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(@RequestParam(required = false) Long ownerId) {
        log.info("getAllForRentOwnerWithParam metodu başladı");
        return forRentOwnerService.getAllForRentOwnerWithParam(Optional.ofNullable(ownerId));
    }

    @GetMapping("/for-rent-owner-cities")
    public List<ListByCityResponseO> getAllForRentCityWithParam(@RequestParam(required = false) Long cityId){
        log.info("getAllForRentCityWithParam metodu başladı");
        return forRentOwnerService.getAllForRentCityWithParam(Optional.ofNullable(cityId));
    }

    @GetMapping("/for-rent-owner-city-name")
    public List<ForRentOwnerResponse> getAllForRentOwnerByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("getAllForRentOwnerByCityName metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} ",cityName);
        return forRentOwnerService.getAllByCityName(cityName,pageable);
    }

    @GetMapping("/for-rent-owner-price")
    public List<ForRentOwnerResponse> getAllByPrice(@RequestParam Long price, Pageable pageable){
        log.info("getAllByPrice metodu başladı");
        log.info("İstek atılan değerler: Fiyat: {}",price);
        return forRentOwnerService.getAllByPrice(price,pageable);
    }

    @GetMapping("/for-rent-owner-listing-date") ///api/for-rent-owner-listing-date?listingDate=22.12.2021
    public List<ForRentOwnerResponse> getAllByListingDate(@RequestParam LocalDate listingDate, Pageable pageable){
        log.info("İstek atılan değerler: Listelenme Tarihi: {}",listingDate);
        log.info("getAllByListingDate metodu başladı");
        return forRentOwnerService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/for-rent-owner-immovables-types")
    public List<ForRentOwnerResponse> getAllByImmovableTypes(@RequestParam ImmovablesTypes immovablesTypes, Pageable pageable){
        log.info("getAllByImmovableTypes metodu başladı");
        log.info("İstek atılan değerler: Konut Tipi: {}",immovablesTypes);
        return forRentOwnerService.findAllByImmovablesTypes(immovablesTypes,pageable);
    }

    @GetMapping("/for-rent-owner-city-district-price")
    public List<ForRentOwnerResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price, Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} , İlçe İsmi: {}, Değeri: {}",price,cityName,district);
        return forRentOwnerService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/for-rent-owner-big-filter")
    public List<ForRentOwnerResponse> bigFilter(@RequestParam Long price,@RequestParam int buildingAge,@RequestParam Boolean balcony,@RequestParam Boolean furnished,@RequestParam String cityName,@RequestParam String district, Pageable pageable) {
        log.info("bigFilter metodu başladı");
        log.info("İstek atılan değerler:Değer:{}, Bina Yaşı : {}, Balkon: {},Eşya: {},Şehir ismi: {} , İlçe İsmi: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forRentOwnerService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }

    @DeleteMapping("/for-rent-owners/{id}")
    public void deleteRent(@PathVariable Long id){
        log.info("deleteRent metodu başladı");
        forRentOwnerService.deleteRent(id);
        log.info("Sahibinden kişisi kiralık konut kaldırdı: {}",id);
    }
}