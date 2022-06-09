package com.graduationproject.realestate.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class EstateAgentRequest {

    @NotEmpty(message = "this field is required")
    private String companyName;

    @NotEmpty(message = "this field is required")
    @Pattern(regexp="^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",message = "phone number format error")
    private String contactNumber;
    private String cityName;

}
