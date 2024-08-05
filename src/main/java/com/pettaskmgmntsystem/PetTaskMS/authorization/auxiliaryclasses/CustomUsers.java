package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "users_credentials")
@Getter
@Setter
public class CustomUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_user")
    private String name;

    @Column(name = "surname_user")
    private String surname;

    @Column(name = "email_user")
    private String email;

    @Column(name = "password_user")
    private String passwordKey;

    @Column(name = "role_user")
    private String role;

    public CustomUsers(int id, String name, String surname, String email, String passwordKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordKey = passwordKey;
    }

    public CustomUsers(int id, String passwordKey, String email) {
        this.id = id;
        this.passwordKey = passwordKey;
        this.email = email;
    }

    public CustomUsers(int id, String passwordKey) {
        this.id = id;
        this.passwordKey = passwordKey;
    }

    public CustomUsers(String email, int... id) {
        this.email = email;
    }

    public CustomUsers(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public CustomUsers(int id) {
        this.id = id;
    }

    public CustomUsers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUsers customUsers = (CustomUsers) o;
        return id == customUsers.id && Objects.equals(name, customUsers.name)
                && Objects.equals(surname, customUsers.surname) && Objects.equals(email, customUsers.email) && Objects.equals(passwordKey, customUsers.passwordKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, passwordKey);
    }

}
