package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

/*import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;*/
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "notess")
@Getter
@Setter
public class Notes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@ManyToOne
    @JoinColumn(name = "user_id")*/
   /* private CustomUsers users;
*/
    @Column(name = "comments")
    private String comments;

    /*public Notes(CustomUsers users, String comments) {
        this.users = users;
        this.comments = comments;
    }*/

   /* public Notes(CustomUsers users) {
        this.users = users;
    }*/

    public Notes(String comments) {
        this.comments = comments;
    }

    public Notes(){
    }
}
