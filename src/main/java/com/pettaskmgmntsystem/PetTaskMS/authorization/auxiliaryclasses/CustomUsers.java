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

    @Column(name = "email_user")
    private String email;

    @Column(name = "password_user")
    private String passwordKey;

    @Column(name = "role_user")
    private String role;

    public CustomUsers(int id, String email, String passwordKey) {
        this.id = id;
        this.email = email;
        this.passwordKey = passwordKey;
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
        CustomUsers that = (CustomUsers) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(passwordKey, that.passwordKey) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, passwordKey, role);
    }
}
