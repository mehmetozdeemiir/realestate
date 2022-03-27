package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import com.graduationproject.realestate.response.ListByCityResponseE;
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
public class ForRentEstateAgentController {
    private final ForRentEstateAgentService forRentEstateAgentService;

    @PostMapping("/for-rent-estate-agents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentEstateAgentResponse addEstateAgentRent(@Valid @RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        log.info("addEstateAgentRent metodu başladı");
        log.info("Emlakçı kiralık konut ekledi:{} ",forRentEstateAgentRequest);
        return forRentEstateAgentService.addEstateAgentRent(forRentEstateAgentRequest);
    }

    @PutMapping("/for-rent-estate-agents/{id}")
    public ForRentEstateAgentResponse updateEstateAgentRent(@PathVariable Long id, @Valid @RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        log.info("updateEstateAgentRent metodu başladı");
        log.info("Emlakçı kiralık konut güncelledi:{} {} ",id,forRentEstateAgentRequest);
        return forRentEstateAgentService.updateEstateAgentRent(id,forRentEstateAgentRequest);
    }

    @GetMapping("/for-rent-estate-agents")
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        log.info("getAllForRentEstateAgentWithParam metodu başladı");
        return forRentEstateAgentService.getAllForRentEstateAgentWithParam(estateAgentId);
    }

    @GetMapping("/for-rent-estate-agent-cityId")
    public List<ListByCityResponseE> getAllForRentCityWithParam(@RequestParam Optional<Long> cityId){
        log.info("getAllForRentCityWithParam metodu başladı");
        return forRentEstateAgentService.getAllForRentCityWithParam(cityId);
    }

   /* @GetMapping("/for-rent-estate-agent-city-name")
    public List<ForRentEstateAgentResponse> getAllByCityName(@RequestParam Optional<String> cityName){
        log.info("getAllByCityName metodu başladı");
        return forRentEstateAgentService.getAllByCityName(cityName);
    } */

    @GetMapping("/for-rent-estate-agent-price/{no}/{size}")
    public List<ForRentEstateAgentResponse> getAllByPrice(@RequestParam Long price,@PathVariable int no, @PathVariable int size ){
        log.info("getAllByPrice metodu başladı");
        log.info("İstek atılan değerler: Fiyat: {}",price);
        return forRentEstateAgentService.getAllByPrice(price,no,size);
    }

    @GetMapping("/for-rent-estate-agent-immovables-types")
    public List<ForRentEstateAgentResponse> getAllByImmovableTypes(@RequestParam ImmovablesTypes immovablesTypes, Pageable pageable){
        log.info("getAllByImmovableTypes metodu başladı");
        log.info("İstek atılan değerler: Konut Tipi: {} ",immovablesTypes);
        return forRentEstateAgentService.findAllByImmovablesTypes(immovablesTypes,pageable);
    }

    @GetMapping("/for-rent-estate-agent-listing-date")
    public List<ForRentEstateAgentResponse> getAllByListingDate(@RequestParam LocalDate listingDate, Pageable pageable) {
        log.info("getAllByListingDate metodu başladı");
        log.info("İstek atılan değerler: Listelenme Tarihi: {}",listingDate);
        return forRentEstateAgentService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/for-rent-estate-agent-city-district-price")
    public List<ForRentEstateAgentResponse> findByCityNameAndDistrictAndPrice(@RequestParam Long price,@RequestParam String cityName, @RequestParam String district,  Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} , İlçe İsmi: {}, Fiyat: {}",price,cityName,district);
        return forRentEstateAgentService.findByCityNameAndDistrictAndPrice(price,cityName,district,pageable);
    }

    @GetMapping("/for-rent-estate-agent-big-filter")
    public List<ForRentEstateAgentResponse> bigFilter(@RequestParam Long price, @RequestParam int buildingAge, @RequestParam Boolean balcony, @RequestParam Boolean furnished, @RequestParam String cityName, @RequestParam String district, Pageable pageable) {
        log.info("bigFilter metodu başladı");
        log.info("İstek atılan değerler: Fiyat:{}, Bina Yaşı : {}, Balkon: {},Eşya: {},Şehir ismi: {} , İlçe İsmi: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forRentEstateAgentService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }

    @DeleteMapping("/for-rent-estate-agents/{id}")
    public void deleteRent(@PathVariable Long id){
        log.info("deleteRent metodu başladı");
        forRentEstateAgentService.deleteRent(id);
        log.info("Emlakçı kiralık konut kaldırdı: {} ",id);
    }
}
