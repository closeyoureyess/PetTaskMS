package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

/*import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;*/
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tasks_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@ManyToOne
    @JoinColumn(name = "tasks_author_id")*/
    /*private CustomUsers taskAuthor;

    @ManyToOne
    @JoinColumn(name = "tasks_executor_id")
    private CustomUsers taskExecutor;*/

    @Column(name = "tasks_priority")
    private String taskPriority;

    @Column(name = "tasks_status")
    private String taskStatus;

    @Column(name = "tasks_header")
    private String header;

    @Column(name = "tasks_description")
    private String description;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tasks_notes_id")*/
   /* private Notes notes;*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return id == tasks.id && Objects.equals(taskPriority, tasks.taskPriority) && Objects.equals(taskStatus, tasks.taskStatus) && Objects.equals(header, tasks.header) && Objects.equals(description, tasks.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskPriority, taskStatus, header, description);
    }
}
