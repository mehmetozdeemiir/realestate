package com.graduationproject.realestate.controller;
import com.graduationproject.realestate.business.abstracts.ForSaleEstateAgentService;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
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
public class ForSaleEstateAgentController {

    private final ForSaleEstateAgentService forSaleEstateAgentService;

    @PostMapping("/for-sale-estate-agents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleEstateAgentResponse addEstateAgentSale(@Valid @RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        log.info("addEstateAgentSale metodu başladı");
        log.info("Emlakçı satılık konut ekledi: {} ",forSaleEstateAgentRequest);
        return forSaleEstateAgentService.addEstateAgentSale(forSaleEstateAgentRequest);
    }

    @PutMapping("/for-sale-estate-agents/{id}")
    public ForSaleEstateAgentResponse updateEstateAgentSale(@PathVariable Long id,@Valid @RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        log.info("updateEstateAgentSale metodu başladı");
        log.info("Emlakçı satılık konut güncelledi: {} {}", id,forSaleEstateAgentRequest);
        return forSaleEstateAgentService.updateEstateAgentSale(id,forSaleEstateAgentRequest);
    }

    @GetMapping("/for-sale-estate-agentss")
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        log.info("getAllForSaleEstateAgentWithParam metodu başladı");
        return forSaleEstateAgentService.getAllForSaleEstateAgentWithParam(estateAgentId);
    }

    @GetMapping("/for-sale-estate-agent-city")
    public List<ListByCityResponseE> getAllForSaleCityWithParam(@RequestParam Optional<Long> cityId){
        log.info("getAllForSaleCityWithParam metodu başladı");
        return forSaleEstateAgentService.getAllForSaleCityWithParam(cityId);
    }

    @GetMapping("/for-sale-estate-agent-city-name")
    public List<ForSaleEstateAgentResponse> getAllByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("getAllByCityName metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} ",cityName);
        return forSaleEstateAgentService.getAllByCityName(cityName,pageable);
    }

    @GetMapping("/for-sale-estate-agent-price")
    public List<ForSaleEstateAgentResponse> getAllByPrice(@RequestParam Long price,Pageable pageable){
        log.info("getAllByPrice metodu başladı");
        log.info("İstek atılan değerler: Fiyat: {}",price);
        return forSaleEstateAgentService.getAllByPrice(price,pageable);
    }

    @GetMapping("/for-sale-estate-agent-cheap-house")
    public List<ForSaleEstateAgentResponse> getAllCheapHouseByCityName(@RequestParam String cityName,Pageable pageable){
        log.info("getAllCheapHouseByCityName metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} ",cityName);
        return forSaleEstateAgentService.getAllCheapHouseByCityName(cityName,pageable);
    }

    @GetMapping("/for-sale-estate-agent-immovables-types")
    public List<ForSaleEstateAgentResponse> getAllByImmovableTypes(@RequestParam ImmovablesTypes immovablesTypes,Pageable pageable){
        log.info("getAllByImmovableTypes metodu başladı");
        log.info("İstek atılan değerler: Konut Tipi: {}",immovablesTypes);
        return forSaleEstateAgentService.findAllByImmovablesTypes(immovablesTypes,pageable);
    }

    @GetMapping("/for-sale-estate-agent-listing-date")
    public List<ForSaleEstateAgentResponse> getAllByListingDate(@RequestParam LocalDate listingDate,Pageable pageable) {
        log.info("getAllByListingDate metodu başladı");
        log.info("İstek atılan değerler: Listelenme Tarihi: {}",listingDate);
        return forSaleEstateAgentService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/for-sale-estate-agent-city-district-price")
    public List<ForSaleEstateAgentResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price,Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice metodu başladı");
        log.info("İstek atılan değerler: Şehir ismi: {} , İlçe İsmi: {}, Fiyat: {}",cityName,district,price);
        return forSaleEstateAgentService.findByCityNameAndDistrictAndPrice(cityName,district,price,pageable);
    }

    @GetMapping("/for-sale-estate-agent-big-filter")
    public List<ForSaleEstateAgentResponse> bigFilter(@RequestParam Long price, @RequestParam int buildingAge, @RequestParam Boolean balcony, @RequestParam Boolean furnished, @RequestParam String cityName, @RequestParam String district,Pageable pageable) {
        log.info("bigFilter metodu başladı");
        log.info("İstek atılan değerler: Fiyat: {}, Bina Yaşı : {}, Balkon: {},Eşya: {},Şehir ismi: {} , İlçe İsmi: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forSaleEstateAgentService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }

    @DeleteMapping("/for-sale-estate-agents/{id}")
    public void deleteSaleEstateAgent(@PathVariable Long id){
        log.info("deleteSaleEstateAgent metodu başladı");
        forSaleEstateAgentService.deleteSaleEstateAgent(id);
        log.info("Emlakçı satılık konut kaldırdı: {}",id);
    }
}

