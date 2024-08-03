package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "users_credentials")
@Getter
@Setter
public class Users {

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

    public Users(int id, String name, String surname, String email, String passwordKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordKey = passwordKey;
    }

    public Users(int id, String passwordKey, String email) {
        this.id = id;
        this.passwordKey = passwordKey;
        this.email = email;
    }

    public Users(int id, String passwordKey) {
        this.id = id;
        this.passwordKey = passwordKey;
    }

    public Users(String email, int... id) {
        this.email = email;
    }

    public Users(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public Users(int id) {
        this.id = id;
    }

    public Users() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(name, users.name)
                && Objects.equals(surname, users.surname) && Objects.equals(email, users.email) && Objects.equals(passwordKey, users.passwordKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, passwordKey);
    }

}
