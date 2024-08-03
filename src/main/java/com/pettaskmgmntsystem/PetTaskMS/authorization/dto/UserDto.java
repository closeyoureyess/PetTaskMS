package com.pettaskmgmntsystem.PetTaskMS.authorization.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class UserDto implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String passwordKey;
    private String email;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
