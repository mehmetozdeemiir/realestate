package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForRentEstateAgentController {
    private final ForRentEstateAgentService forRentEstateAgentService;

    @PostMapping("/for-rent-estate-agents")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentEstateAgentResponse addEstateAgentRent( @RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        return forRentEstateAgentService.addEstateAgentRent(forRentEstateAgentRequest);
    }

    @PutMapping("/for-rent-estate-agents/{id}")
    public ForRentEstateAgentResponse updateEstateAgentRent(@Valid @PathVariable Long id,@RequestBody ForRentEstateAgentRequest forRentEstateAgentRequest){
        return forRentEstateAgentService.updateEstateAgentRent(id,forRentEstateAgentRequest);
    }

    @GetMapping("/for-rent-estate-agents")
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(@RequestParam Optional<Long> estateAgentId){
        return forRentEstateAgentService.getAllForRentEstateAgentWithParam(estateAgentId);
    }

    @DeleteMapping("/for-rent-estate-agents/{id}")
    public void deleteRent(@PathVariable Long id){
        forRentEstateAgentService.deleteRent(id);
    }
}
