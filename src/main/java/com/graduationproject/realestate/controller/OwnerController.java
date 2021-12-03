package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.OwnerService;
import com.graduationproject.realestate.request.OwnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerRequest> getAllOwners(){
        return ownerService.getAllOwner();
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerRequest addOwner(@RequestBody OwnerRequest ownerRequest){
        return ownerService.addOwner(ownerRequest);
    }

    @PutMapping("/owners")
    public OwnerRequest updateOwner(@PathVariable Long id ,@RequestBody OwnerRequest ownerRequest){
        return ownerService.updateOwner(id, ownerRequest);
    }

    @DeleteMapping
    public void deleteOwner(@PathVariable Long id){
        ownerService.deleteOwner(id);
    }


}
