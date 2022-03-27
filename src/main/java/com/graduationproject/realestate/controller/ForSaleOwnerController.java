package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.entities.ImmovablesTypes;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForSaleOwnerController {

    private final ForSaleOwnerService forSaleOwnerService;

    @PostMapping("/for-sale-owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleOwnerResponse addOwnerSale(@Valid @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        log.info("addOwnerSale metodu başladı");
        log.info("Sahibinden kişisi satılık ev ekledi: {} ",forSaleOwnerRequest);
        return forSaleOwnerService.addOwnerSale(forSaleOwnerRequest);
    }

    @PutMapping("/for-sale-owners/{id}")
    public ForSaleOwnerResponse updateOwnerSale(@PathVariable Long id,@Valid @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        log.info("updateOwnerSale metodu başladı");
        log.info("Sahibinden kişisi satılık ev güncelledi: {} {}",id,forSaleOwnerRequest);
        return forSaleOwnerService.updateOwnerSale(id,forSaleOwnerRequest);
    }

    @GetMapping("/for-sale-owner-300under")
    public List<ForSaleOwnerResponse> getAllUnder300Thousand(Pageable pageable){
        log.info("getAllUnder300Thousand metodu başladı");
        return forSaleOwnerService.under300thousand(pageable);
    }

    @GetMapping("/for-sale-owner-price")
    public List<ForSaleOwnerResponse> findAllByPrice(@RequestParam Long price,Pageable pageable) {
        log.info("getAllForSalePrice metodu başladı");
        log.info("İstek atılan değerler: Değer: {} ",price);
        return forSaleOwnerService.findAllByPrice(price,pageable);
    }

    @GetMapping("/for-sale-owner-city-name")
    public List<ForSaleOwnerResponse> findByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("findByCityName metodu başladı");
        log.info("İstek atılan değerler: Şehir İsmi: {} ",cityName);
        return forSaleOwnerService.findByCityName(cityName,pageable);
    }

    @GetMapping("/for-sale-owner-cheap-house")
    public List<ForSaleOwnerResponse> getAllCheapHouseByCityName(@RequestParam String cityName){
        log.info("getAllCheapHouseByCityName metodu başladı");
        log.info("İstek atılan değerler: Şehir İsmi: {} ",cityName);
        return forSaleOwnerService.getAllCheapHouseByCityName(cityName);
    }

    @GetMapping("/for-sale-owner-immovables-types")
    public List<ForSaleOwnerResponse> getAllByImmovableTypes(@RequestParam ImmovablesTypes immovablesTypes,Pageable pageable){
        log.info("getAllByImmovableTypes metodu başladı");
        log.info("İstek atılan değerler: Konut Tipi: {} ",immovablesTypes);
        return forSaleOwnerService.findAllByImmovablesTypes(immovablesTypes,pageable);
    }

    @GetMapping("/for-sale-owner-listing-date")
    public List<ForSaleOwnerResponse> getAllByListingDate(@RequestParam LocalDate listingDate,Pageable pageable) {
        log.info("getAllByListingDate metodu başladı");
        log.info("İstek atılan değerler: Listelenme Tarihi: {} ",listingDate);
        return forSaleOwnerService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/for-sale-owner-city-price")
    public List<ForSaleOwnerResponse> findByCityNameAndPrice(@RequestParam Long price,@RequestParam String cityName,  Pageable pageable){
        log.info("findByCityNameAndPrice metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} , Değeri: {}",cityName,price);
        return forSaleOwnerService.findByCityNameAndPrice(price,cityName,pageable);
    }

    @GetMapping("/for-sale-owner-city-district-price")
    public List<ForSaleOwnerResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price,Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} , İlçe İsmi: {}, Değeri: {}",cityName,district,price);
        return forSaleOwnerService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/for-sale-owner-big-filter")
    public List<ForSaleOwnerResponse> bigFilter(@RequestParam Long price,@RequestParam int buildingAge,@RequestParam Boolean balcony,@RequestParam Boolean furnished,@RequestParam String cityName,@RequestParam String district,Pageable pageable){
        log.info("bigFilter metodu başladı");
        log.info("İstek atılan değerler:Değer:{}, Bina Yaşı : {}, Balkon: {},Eşya: {},Şehir ismi: {} , İlçe İsmi: {}",price,buildingAge,balcony,furnished,cityName,district);
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

    @DeleteMapping("/for-sale-owners/{id}")
    public void deleteSale(@PathVariable Long id){
        log.info("deleteSale metodu başladı");
        forSaleOwnerService.deleteSaleOwner(id);
        log.info("Sahibinden kişisi satılık konut kaldırdı: {} ",id);
    }
}
