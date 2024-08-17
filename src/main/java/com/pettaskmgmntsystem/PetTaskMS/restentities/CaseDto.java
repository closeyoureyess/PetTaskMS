package com.pettaskmgmntsystem.PetTaskMS.restentities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CaseDto implements Serializable {

    private int id;
    private LocalDateTime dateOfCreate;
    private String name;

    public CaseDto(Integer id, String name, LocalDateTime dateOfCreate) {
        this.id = id;
        this.name = name;
        this.dateOfCreate = dateOfCreate;
    }

    public CaseDto(String name, LocalDateTime dateOfCreate) {
        this.name = name;
        this.dateOfCreate = dateOfCreate;
    }

    public CaseDto(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public CaseDto(String name) {
        this.name = name;
    }

    public CaseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseDto caseDto = (CaseDto) o;
        return id == caseDto.id && Objects.equals(name, caseDto.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
