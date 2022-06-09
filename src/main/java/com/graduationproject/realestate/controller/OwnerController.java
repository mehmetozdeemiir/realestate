package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.OwnerService;
import com.graduationproject.realestate.request.OwnerRequest;
import com.graduationproject.realestate.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/api/owners")
@RequiredArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerResponse> getAllOwners(){
        log.info("getAllOwners method started");
        return ownerService.getAllOwner();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponse addOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        log.info("addOwner method started");
        log.info("added: {} ",ownerRequest);
        return ownerService.addOwner(ownerRequest);
    }

    @PutMapping("/{id}")
    public OwnerResponse updateOwner(@PathVariable Long id ,@Valid @RequestBody OwnerRequest ownerRequest){
        log.info("updateOwner method started");
        log.info("updated: {},{}",id,ownerRequest);
        return ownerService.updateOwner(id, ownerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id){
        log.info("deleteOwner method started");
        log.info("deleted: {} ",id);
        ownerService.deleteOwner(id);
    }


}
