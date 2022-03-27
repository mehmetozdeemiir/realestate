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

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public List<OwnerResponse> getAllOwners(){
        log.info("getAllOwners metodu başladı");
        return ownerService.getAllOwner();
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponse addOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        log.info("addOwner metodu başladı");
        log.info("Owner eklendi: ",ownerRequest);
        return ownerService.addOwner(ownerRequest);
    }

    @PutMapping("/owners/{id}")
    public OwnerResponse updateOwner(@PathVariable Long id ,@Valid @RequestBody OwnerRequest ownerRequest){
        log.info("updateOwner metodu başladı");
        log.info("Owner güncellendi: ",id,ownerRequest);
        return ownerService.updateOwner(id, ownerRequest);
    }

    @DeleteMapping("/owners/{id}")
    public void deleteOwner(@PathVariable Long id){
        log.info("deleteOwner metodu başladı");
        log.info("Owner silindi: ",id);
        ownerService.deleteOwner(id);
    }


}
