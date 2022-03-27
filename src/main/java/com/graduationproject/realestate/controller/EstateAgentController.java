package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.EstateAgentService;
import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.response.EstateAgentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstateAgentController {
    private final EstateAgentService estateAgentService;

    @GetMapping("/estateagents")
    public List<EstateAgentResponse> getAllEstateAgents(){
        log.info("getAllEstateAgents metodu başladı");
        return estateAgentService.getAllEstateAgent();
    }

    @PostMapping("/estateagents")
    @ResponseStatus(HttpStatus.CREATED)
    public EstateAgentResponse addEstateAgent( @RequestBody EstateAgentRequest estateAgentRequest){
        log.info("addEstateAgent metodu başladı");
        log.info("Emlakçı eklendi: {}",estateAgentRequest);
        return estateAgentService.addEstateAgent(estateAgentRequest);
    }

    @PutMapping("/estateagents/{id}")
    public EstateAgentResponse updateEstateAgent(@Valid @PathVariable Long id, @RequestBody EstateAgentRequest estateAgentRequest){
        log.info("updateEstateAgent metodu başladı");
        log.info("Estate Agent güncellendi: {} {}",id,estateAgentRequest);
        return estateAgentService.updateEstateAgent(id,estateAgentRequest);
    }

    @DeleteMapping("/estateagents/{id}")
    public void deleteEstateAgents(@PathVariable Long id){
        log.info("deleteEstateAgent metodu başladı");
        log.info("Estate Agent silindi: {}",id);
        estateAgentService.deleteEstateAgent(id);
    }



}
