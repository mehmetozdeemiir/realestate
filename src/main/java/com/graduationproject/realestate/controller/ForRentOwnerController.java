package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForRentOwnerService;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
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
public class ForRentOwnerController {

    private final ForRentOwnerService forRentOwnerService;

    @PostMapping("/for-rent-owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForRentOwnerResponse addOwnerRent(@Valid @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        return forRentOwnerService.addOwnerRent(forRentOwnerRequest);
    }

    @PutMapping("/for-rent-owners/{id}")
    public ForRentOwnerResponse updateOwnerRent(@Valid @PathVariable Long id, @RequestBody ForRentOwnerRequest forRentOwnerRequest){
        return forRentOwnerService.updateOwnerRent(id,forRentOwnerRequest);
    }
    @GetMapping("/for-rent-owners")
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(@RequestParam(required = false) Long ownerId)//bunu araştır düzelt düzeltttttttt hepsini bunun gibi yap
    {
        return forRentOwnerService.getAllForRentOwnerWithParam(Optional.ofNullable(ownerId));
    }

    @DeleteMapping("/for-rent-owners/{id}")
    public void deleteRent(@PathVariable Long id){
        forRentOwnerService.deleteRent(id);
    }
}
