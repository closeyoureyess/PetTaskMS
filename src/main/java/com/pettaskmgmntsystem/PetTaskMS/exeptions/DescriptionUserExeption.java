package com.pettaskmgmntsystem.PetTaskMS.exeptions;

public enum DescriptionUserExeption {

    USER_NOT_FOUND("Пользователь не найден. Попробуйте ещё раз"),

    EXECUTOR_NOT_SPECIFIED("Произошла ошибка из-за отсутствующего исполнителя у задачи. Попробуйте ещё раз."),

    NOT_ENOUGH_RULES("Для осуществления действия недостаточно прав на сущность"),
    NOT_ENOUGH_RULES_EXECUTOR("Для осуществления действия недостаточно прав на сущность, исполнитель может редактировать только статус задачи");

    private String enumDescription;

    DescriptionUserExeption(String enumDescription) {
        this.enumDescription = enumDescription;
    }

    public String getEnumDescription() {
        return enumDescription;
    }
}
