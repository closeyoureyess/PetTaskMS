package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;


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

    @Column(name = "name_user")
    private String name;

    @Column(name = "surname_user")
    private String surname;

    @Column(name = "email_user")
    private String email;

    @Column(name = "password_user")
    private String passwordKey;

    public User(int id, String name, String surname, String email, String passwordKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordKey = passwordKey;
    }

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

    public User(int id) {
        this.id = id;
    }

    public User(){
    }

}
