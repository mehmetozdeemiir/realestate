package com.graduationproject.realestate.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class EstateAgentRequest {

    @NotEmpty(message = "Şirket ismi alanı boş bırakılamaz.")
    private String companyName;

    @NotEmpty(message = "İletişim numarası alanı boş bırakılamaz")
    @Pattern(regexp="^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",message = "Telefon numarası format hatası")
    private String contactNumber;

    private String cityName;

    public EstateAgentRequest( String companyName, String contactNumber) {
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }
}
