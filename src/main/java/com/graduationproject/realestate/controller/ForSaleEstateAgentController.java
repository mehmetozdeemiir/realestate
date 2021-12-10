package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForSaleEstateAgentService;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;

import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForSaleEstateAgentController {

    private final ForSaleEstateAgentService forSaleEstateAgentService;

    @PostMapping("/forsaleestateagents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleEstateAgentResponse addEstateAgentSale(@Valid @RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        return forSaleEstateAgentService.addEstateAgentSale(forSaleEstateAgentRequest);
    }

    @PutMapping("/forsaleestateagents")
    public ForSaleEstateAgentResponse updateEstateAgentSale(@Valid@PathVariable Long id,@RequestBody ForSaleEstateAgentRequest forSaleEstateAgentRequest){
        return forSaleEstateAgentService.updateEstateAgentSale(id,forSaleEstateAgentRequest);
    }

    @GetMapping("/forsaleestateagentss")
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        return forSaleEstateAgentService.getAllForSaleEstateAgentWithParam(estateAgentId);
    }

    @DeleteMapping("/for-sale-estate-agents/{id}")
    public void deleteSaleEstateAgent(@PathVariable Long id){
        forSaleEstateAgentService.deleteSaleEstateAgent(id);
    }
}
