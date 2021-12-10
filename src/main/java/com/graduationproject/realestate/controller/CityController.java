package com.graduationproject.realestate.controller;
import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/cities")
    public List<CityResponse> getAllCity(){
        return cityService.getAllCity();
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponse addCity(@Valid @RequestBody CityRequest cityRequest){
        return cityService.addCity(cityRequest);
    }

    @PutMapping("/cities/{id}")
    public CityResponse updateCity(@Valid @PathVariable Long id, @RequestBody CityRequest cityRequest){
        return cityService.updateCity(id, cityRequest);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCity(@PathVariable Long id){
         cityService.deleteCity(id);
    }
}
