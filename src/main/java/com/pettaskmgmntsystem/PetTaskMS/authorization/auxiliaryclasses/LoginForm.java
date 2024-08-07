package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {

    private String email;
    private String passwordKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginForm loginForm = (LoginForm) o;
        return Objects.equals(email, loginForm.email) && Objects.equals(passwordKey, loginForm.passwordKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, passwordKey);
    }
}
