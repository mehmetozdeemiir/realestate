package com.graduationproject.realestate.controller;
import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.response.CityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/api/cities")
@RequiredArgsConstructor
@Slf4j
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityResponse> getAllCity(){
        log.info("getAllCity method started");
        return cityService.getAllCity();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponse addCity(@Valid @RequestBody CityRequest cityRequest){
        log.info("addCity method started");
        log.info("City added: {} ", cityRequest);
        return cityService.addCity(cityRequest);
    }

    @PutMapping("/{id}")
    public CityResponse updateCity(@Valid @PathVariable Long id, @RequestBody CityRequest cityRequest){
        log.info("updateCity method started");
        log.info("City updated: {} {}", id,cityRequest);
        return cityService.updateCity(id, cityRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id){
        log.info("deleteCity method started");
        log.info("City deleted: {} ", id);
        cityService.deleteCity(id);
    }
}
