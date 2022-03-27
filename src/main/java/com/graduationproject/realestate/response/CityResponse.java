package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.City;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CityResponse {
    private String cityName;
    private String district;

}
