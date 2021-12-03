package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.EstateAgentService;
import com.graduationproject.realestate.request.EstateAgentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstateAgentController {
    private final EstateAgentService estateAgentService;

    @GetMapping("/estateagents")
    public List<EstateAgentRequest> getAllEstateAgents(){
        return estateAgentService.getAllEstateAgent();
    }

    @PostMapping("/estateagents")
    @ResponseStatus(HttpStatus.CREATED)
    public EstateAgentRequest addEstateAgent(@RequestBody EstateAgentRequest estateAgentRequest){
        return estateAgentService.addEstateAgent(estateAgentRequest);
    }

    @PutMapping("/estateagents")
    public EstateAgentRequest updateEstateAgent(@PathVariable Long id, @RequestBody EstateAgentRequest estateAgentRequest){
        return estateAgentService.updateEstateAgent(id,estateAgentRequest);
    }

    @DeleteMapping("/estateagents/{id}")
    public void deleteEstateAgents(@PathVariable Long id){
        estateAgentService.deleteEstateAgent(id);
    }



}
