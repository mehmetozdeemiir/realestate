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


@RestController
@RequestMapping("/v1/api/estate-agents")
@RequiredArgsConstructor
@Slf4j
public class EstateAgentController {
    private final EstateAgentService estateAgentService;

    @GetMapping
    public List<EstateAgentResponse> getAllEstateAgents(){
        log.info("getAllEstateAgents method started");
        return estateAgentService.getAllEstateAgent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstateAgentResponse addEstateAgent(@Valid @RequestBody EstateAgentRequest estateAgentRequest){
        log.info("addEstateAgent method started");
        log.info("Estate agent added: {}",estateAgentRequest);
        return estateAgentService.addEstateAgent(estateAgentRequest);
    }

    @PutMapping("/{id}")
    public EstateAgentResponse updateEstateAgent( @PathVariable Long id, @Valid @RequestBody EstateAgentRequest estateAgentRequest){
        log.info("updateEstateAgent method started");
        log.info("Estate agent updated: {} {}",id,estateAgentRequest);
        return estateAgentService.updateEstateAgent(id,estateAgentRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEstateAgents(@PathVariable Long id){
        log.info("deleteEstateAgent method started");
        log.info("Estate agent deleted: {}",id);
        estateAgentService.deleteEstateAgent(id);
    }



}
