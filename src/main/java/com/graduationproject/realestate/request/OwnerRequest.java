package com.graduationproject.realestate.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class OwnerRequest {
    @NotEmpty(message = "İsim alanı boş bırakılamaz")
    @Size(min=1,max=30)
    private String firstName;

    @NotEmpty(message = "Soyisim alanı boş bırakılamaz")
    @Size(min = 1,max=40)
    private String lastName;

    @NotEmpty(message = "İletişim numarası alanı boş bırakılamaz")
    @Pattern(regexp="^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",message = "Telefon numarası format hatası")
    private String contactNumber;
    //şehir ismine gerek yok satan veya kiralayan hangi şehirde olduğu çok önemli değil gibi gerekirse eklenir



}
