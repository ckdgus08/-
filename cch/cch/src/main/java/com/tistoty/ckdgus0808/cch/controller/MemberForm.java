package com.tistoty.ckdgus0808.cch.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
