package com.pettaskmgmntsystem.PetTaskMS.authorization.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class CustomUsersDto implements Serializable {

    private int id;

    private String passwordKey;
    private String email;

    public CustomUsersDto(int id, String passwordKey, String email) {
        this.id = id;
        this.passwordKey = passwordKey;
        this.email = email;
    }
    public CustomUsersDto(String passwordKey, String email) {

        this.passwordKey = passwordKey;
        this.email = email;
    }

    public CustomUsersDto(int id) {
        this.id = id;
    }

    public CustomUsersDto() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
