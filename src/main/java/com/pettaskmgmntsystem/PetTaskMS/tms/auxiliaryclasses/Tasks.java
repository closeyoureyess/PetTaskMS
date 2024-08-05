package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

/*import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;*/
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tasks_entity")
@Getter
@Setter
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
    private Notes notes;

    public Tasks(int id, /*CustomUsers taskAuthor, CustomUsers taskExecutor,*/ String taskPriority, String taskStatus, String header, String description, Notes notes) {
        this.id = id;
       /* this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;*/
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.header = header;
        this.description = description;
        this.notes = notes;
    }

    public Tasks(int id, /*CustomUsers taskAuthor, CustomUsers taskExecutor,*/ String taskPriority, String taskStatus, String header, String description) {
        this.id = id;
        /*this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;*/
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.header = header;
        this.description = description;
    }

    public Tasks(int id, /*CustomUsers taskAuthor, CustomUsers taskExecutor,*/ String taskPriority, String taskStatus, String header) {
        this.id = id;
      /*  this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;*/
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.header = header;
    }

    public Tasks(int id,/* CustomUsers taskAuthor, CustomUsers taskExecutor,*/ String taskPriority, String taskStatus) {
        this.id = id;
        /*this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;*/
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
    }

    public Tasks(int id, /*CustomUsers taskAuthor, CustomUsers taskExecutor,*/ String taskPriority) {
        this.id = id;
       /* this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;*/
        this.taskPriority = taskPriority;
    }

   /* public Tasks(int id, CustomUsers taskAuthor, CustomUsers taskExecutor) {
        this.id = id;
        this.taskAuthor = taskAuthor;
        this.taskExecutor = taskExecutor;
    }*/

    /*public Tasks(int id, CustomUsers taskAuthor) {
        this.id = id;
        this.taskAuthor = taskAuthor;
    }*/

    public Tasks(int id) {
        this.id = id;
    }

    public Tasks(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return id == tasks.id && Objects.equals(taskPriority, tasks.taskPriority) && Objects.equals(taskStatus, tasks.taskStatus) && Objects.equals(header, tasks.header) && Objects.equals(description, tasks.description) && Objects.equals(notes, tasks.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskPriority, taskStatus, header, description, notes);
    }
}
