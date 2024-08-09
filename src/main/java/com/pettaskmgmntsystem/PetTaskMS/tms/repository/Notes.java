package com.pettaskmgmntsystem.PetTaskMS.tms.repository;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes_comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUsers users;


    @Column(name = "comments")
    private String comments;


    public Notes(CustomUsers users, String comments) {
        this.users = users;
        this.comments = comments;
    }
}
