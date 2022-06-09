package com.graduationproject.realestate.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class OwnerRequest {
    @NotEmpty(message = "this field is required")
    @Size(min=1,max=30)
    private String firstName;

    @NotEmpty(message = "this field is required")
    @Size(min = 1,max=40)
    private String lastName;

    @NotEmpty(message = "this field is required")
    @Pattern(regexp="^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",message = "phone number format error")
    private String contactNumber;



}
