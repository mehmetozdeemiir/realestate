package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentService;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentCityResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForRentController {
    private final ForRentService forRentService;

    @PostMapping("/forrentowners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentOwnerRequest addOwnerRent(@Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        return forRentService.addOwnerRent(forRentOwnerRequest);
    }

    @PutMapping("/forrentowners")
    public ForRentOwnerRequest updateOwnerRent(@PathVariable Long id, @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        return forRentService.updateOwnerRent(id,forRentOwnerRequest);
    }

    @PostMapping("/forrentestateagents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentEstateAgentRequest addEstateAgentRent(@RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        return forRentService.addEstateAgentRent(forRentEstateAgentRequest);
    }

    @PutMapping("/forrentestateagents")
    public ForRentEstateAgentRequest updateEstateAgentRent(@PathVariable Long id,@RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        return forRentService.updateEstateAgentRent(id,forRentEstateAgentRequest);
    }

    @GetMapping("/forrentowners")
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(@RequestParam Optional<Long> ownerId){
        return forRentService.getAllForRentOwnerWithParam(ownerId);
    }

    @GetMapping("/forrentcities")
    public List<ForRentCityResponse> getAllForRentCityWithParam(Optional<Long> cityId){
        return forRentService.getAllForRentCityWithParam(cityId);
    }

    @GetMapping("/forrentestateagents")
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId){
        return forRentService.getAllForRentEstateAgentWithParam(estateAgentId);
    }

}
