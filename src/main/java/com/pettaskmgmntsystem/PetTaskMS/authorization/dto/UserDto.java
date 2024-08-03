package com.pettaskmgmntsystem.PetTaskMS.authorization.dto;

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

    public UserDto(int id, String name, String surname, String passwordKey, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passwordKey = passwordKey;
        this.email = email;
    }

    public UserDto(int id, String name, String surname, String passwordKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passwordKey = passwordKey;
    }

    public UserDto(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UserDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    public UserDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(String name, String... surname) {
        this.name = name;
    }

    public UserDto(String surname) {
        this.surname = surname;
    }

    public UserDto(int id) {
        this.id = id;
    }

    public UserDto() {
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
