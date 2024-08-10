package com.pettaskmgmntsystem.PetTaskMS.exeptions;

public enum DescriptionUserExeption {

    USER_NOT_FOUND("Пользователь не найден. Попробуйте ещё раз");

    private String enumUser;

    DescriptionUserExeption(String enumUser) {
        this.enumUser = enumUser;
    }

    public String getEnumUser() {
        return enumUser;
    }
}
