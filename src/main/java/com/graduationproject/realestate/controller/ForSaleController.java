package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForSaleService;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForSaleController {
    private final ForSaleService forSaleService;

    @PostMapping("/forsaleowners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleOwnerRequest addOwnerSale(@RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        return forSaleService.addOwnerSale(forSaleOwnerRequest);
    }

    @PutMapping("/forsaleowners")
    public ForSaleOwnerRequest updateOwnerSale(@PathVariable Long id, @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        return forSaleService.updateOwnerSale(id,forSaleOwnerRequest);
    }

    @PostMapping("/forsaleestateagents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleEstateAgentRequest addEstateAgentSale(@RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        return forSaleService.addEstateAgentSale(forSaleEstateAgentRequest);
    }

    @PutMapping("/forsaleestateagents")
    public ForSaleEstateAgentRequest updateEstateAgentSale(@PathVariable Long id,@RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        return forSaleService.updateEstateAgentSale(id,forSaleEstateAgentRequest);
    }

    @GetMapping("/forsaleowners")
    public List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(@RequestParam Optional<Long> ownerId){
        return forSaleService.getAllForSaleOwnerWithParam(ownerId);
    }

    @GetMapping("/forsalecities")
    public List<ForSaleCityResponse> getAllForSaleCityWithParam(Optional<Long> cityId){
        return forSaleService.getAllForSaleCityWithParam(cityId);
    }

    @GetMapping("/forsaleestateagents")
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId){
        return forSaleService.getAllForSaleEstateAgentWithParam(estateAgentId);
    }
}
