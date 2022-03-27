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

@Slf4j
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
        log.info("addCity metodu başladı");
        log.info("Eklendi: {} ", cityRequest);
        return cityService.addCity(cityRequest);
    }

    @PutMapping("/cities/{id}")
    public CityResponse updateCity(@Valid @PathVariable Long id, @RequestBody CityRequest cityRequest){
        log.info("updateCity metodu başladı");
        log.info("Güncellendi: {} {}", id,cityRequest);
        return cityService.updateCity(id, cityRequest);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCity(@PathVariable Long id){
        log.info("deleteCity metodu başladı");
        log.info("Silindi: {} ", id);
        cityService.deleteCity(id);
    }
}
