package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasees;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_credentials")
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password_user")
    private String passwordKey;

    @Column(name = "email_user")
    private String email;

    public User(int id, String passwordKey, String email){
        this.id = id;
        this.passwordKey = passwordKey;
        this.email = email;
    }

    public User(int id, String passwordKey){
        this.id = id;
        this.passwordKey = passwordKey;
    }

    public User(String email, int... id){
        this.email = email;
    }

    public User(String passwordKey){
        this.passwordKey = passwordKey;
    }

    public User(){
    }
}
