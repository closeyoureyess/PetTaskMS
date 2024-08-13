package com.pettaskmgmntsystem.PetTaskMS.authorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUsersDto implements Serializable {

    private Integer id;

    private String passwordKey;

    private String email;

    private String role;
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
