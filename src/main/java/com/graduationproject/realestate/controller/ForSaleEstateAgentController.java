package com.graduationproject.realestate.controller;
import com.graduationproject.realestate.business.abstracts.ForSaleEstateAgentService;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
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
@RequestMapping("/v1/api/for-sale-estate-agents")
@RequiredArgsConstructor
@Slf4j
public class ForSaleEstateAgentController {

    private final ForSaleEstateAgentService forSaleEstateAgentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleEstateAgentResponse addEstateAgentSale(@Valid @RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        log.info("addEstateAgentSale method started");
        log.info("added: {} ",forSaleEstateAgentRequest);
        return forSaleEstateAgentService.addEstateAgentSale(forSaleEstateAgentRequest);
    }

    @PutMapping("/{id}")
    public ForSaleEstateAgentResponse updateEstateAgentSale(@PathVariable Long id,@Valid @RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        log.info("updateEstateAgentSale method started");
        log.info("updated: {} {}", id,forSaleEstateAgentRequest);
        return forSaleEstateAgentService.updateEstateAgentSale(id,forSaleEstateAgentRequest);
    }

    @GetMapping
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        log.info("getAllForSaleEstateAgentWithParam method started");
        return forSaleEstateAgentService.getAllForSaleEstateAgentWithParam(estateAgentId);
    }

    @GetMapping("/city-name")
    public List<ForSaleEstateAgentResponse> getAllByCityName(@RequestParam String cityName, Pageable pageable){
        log.info("getAllByCityName method started");
        log.info("Requested values: City Name: {} ",cityName);
        return forSaleEstateAgentService.getAllByCityName(cityName,pageable);
    }

    @GetMapping("/price")
    public List<ForSaleEstateAgentResponse> getAllByPrice(@RequestParam Long price,Pageable pageable){
        log.info("getAllByPrice method started");
        log.info("Requested values: Price: {}",price);
        return forSaleEstateAgentService.getAllByPrice(price,pageable);
    }

    @GetMapping("/cheap-house")
    public List<ForSaleEstateAgentResponse> getAllCheapHouseByCityName(@RequestParam String cityName,Pageable pageable){
        log.info("getAllCheapHouseByCityName method started");
        log.info("Requested values: City Name: {} ",cityName);
        return forSaleEstateAgentService.getAllCheapHouseByCityName(cityName,pageable);
    }

    @GetMapping("/product-types")
    public List<ForSaleEstateAgentResponse> getAllByProductType(@RequestParam ProductType productType, Pageable pageable){
        log.info("getAllByImmovableTypes method started");
        log.info("Requested values: Product Type: {}", productType);
        return forSaleEstateAgentService.findAllByProductType(productType,pageable);
    }

    @GetMapping("/listing-date")
    public List<ForSaleEstateAgentResponse> getAllByListingDate(@RequestParam LocalDate listingDate,Pageable pageable) {
        log.info("getAllByListingDate method started");
        log.info("Requested values: Listing Date: {}",listingDate);
        return forSaleEstateAgentService.getAllByListingDate(listingDate,pageable);
    }

    @GetMapping("/city-district-price")
    public List<ForSaleEstateAgentResponse> findByCityNameAndDistrictAndPrice(@RequestParam String cityName, @RequestParam String district, @RequestParam Long price,Pageable pageable){
        log.info("findByCityNameAndDistrictAndPrice method started");
        log.info("Requested values: City Name: {} ,District Name: {} , Price : {} ",cityName,district,price);
        return forSaleEstateAgentService.findByCityNameAndDistrictAndPrice(cityName,district,price,pageable);
    }

    @GetMapping("/big-filter")
    public List<ForSaleEstateAgentResponse> bigFilter(@RequestParam Long price, @RequestParam int buildingAge, @RequestParam Boolean balcony, @RequestParam Boolean furnished, @RequestParam String cityName, @RequestParam String district,Pageable pageable) {
        log.info("bigFilter method started");
        log.info("Requested values: Price: {}, Building Age : {}, Balcony: {},Furnished: {},City Name: {} ,District Name: {}", price, buildingAge, balcony, furnished, cityName, district);
        return forSaleEstateAgentService.bigFilter(price, buildingAge, balcony, furnished, cityName, district,pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteSaleEstateAgent(@PathVariable Long id){
        log.info("deleteSaleEstateAgent method started");
        forSaleEstateAgentService.deleteSaleEstateAgent(id);
        log.info("deleted: {}",id);
    }
}

