package com.pettaskmgmntsystem.PetTaskMS.authorization.repository;


import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users_credentials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany
    private List<Tasks> allTasks;

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
