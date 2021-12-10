package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.OwnerService;
import com.graduationproject.realestate.request.OwnerRequest;
import com.graduationproject.realestate.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public List<OwnerResponse> getAllOwners(){
        return ownerService.getAllOwner();
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponse addOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        return ownerService.addOwner(ownerRequest);
    }

    @PutMapping("/owners/{id}")
    public OwnerResponse updateOwner(@Valid@PathVariable Long id ,@RequestBody OwnerRequest ownerRequest){
        return ownerService.updateOwner(id, ownerRequest);
    }

    @DeleteMapping("/owners/{id}")
    public void deleteOwner(@PathVariable Long id){
        ownerService.deleteOwner(id);
    }


}
