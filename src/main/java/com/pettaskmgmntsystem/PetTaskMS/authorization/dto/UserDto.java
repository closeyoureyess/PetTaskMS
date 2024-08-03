package com.pettaskmgmntsystem.PetTaskMS.authorization.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class UserDto implements Serializable {

    private int id;
    private String passwordKey;
    private String email;

}
