package com.graduationproject.realestate.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CityRequest {

    private String cityName;
    private String district;

}
