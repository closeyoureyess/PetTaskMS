package com.pettaskmgmntsystem.PetTaskMS.exeptions;

public enum DescriptionUserExeption {

    USER_NOT_FOUND("Пользователь не найден. Попробуйте ещё раз"),

    EXECUTOR_NOT_SPECIFIED("Произошла ошибка из-за отсутствующего исполнителя у задачи. Попробуйте ещё раз.");

    private String enumDescription;

    DescriptionUserExeption(String enumDescription) {
        this.enumDescription = enumDescription;
    }

    public String getEnumDescription() {
        return enumDescription;
    }
}
