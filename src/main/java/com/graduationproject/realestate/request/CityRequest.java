package com.graduationproject.realestate.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class CityRequest {
    @NotEmpty(message = "this field is required")
    private String cityName;
    @NotEmpty(message = "this field is required")
    private String district;

}
