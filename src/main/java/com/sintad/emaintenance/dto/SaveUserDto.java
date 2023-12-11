package com.sintad.emaintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
