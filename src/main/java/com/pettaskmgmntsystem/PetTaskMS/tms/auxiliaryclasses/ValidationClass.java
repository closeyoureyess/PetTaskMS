package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationClass {

    private String validationString;

    private Integer validationInteger;

    public Optional<ValidationClass> validEmailOrId(String line){
        boolean resultMatch;

        Pattern pattern = Pattern.compile(ConstantsClass.REGEX_ONLY_NUMBERS);
        Matcher matcher = pattern.matcher(line);
        resultMatch = matcher.find();

        if (resultMatch){
            return Optional.of(new ValidationClass(Integer.valueOf(line)));
        }

        pattern = Pattern.compile(ConstantsClass.REGEX_EMAIL);
        matcher = pattern.matcher(line);
        resultMatch = matcher.find();

        if (resultMatch){
            return Optional.of(new ValidationClass(line));
        }

        return Optional.empty();
    }

    public ValidationClass(Integer validationId) {
        this.validationInteger = validationId;
    }

    public ValidationClass(String validationEmail) {
        this.validationString = validationEmail;
    }
}
