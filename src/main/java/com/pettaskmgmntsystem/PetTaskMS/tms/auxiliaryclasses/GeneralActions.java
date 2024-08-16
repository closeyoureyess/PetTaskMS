package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import org.springframework.stereotype.Component;

@Component
public class GeneralActions {
    public boolean compareIntWithConstants(Integer objectInt, Integer constantsInt) {
        if (constantsInt.equals(ConstantsClass.REGIME_RECORD) && objectInt.equals(constantsInt)) {
            return true;
        } else if (constantsInt.equals(ConstantsClass.REGIME_OVERWRITING) && objectInt.equals(constantsInt)) {
            return true;
        }
        return false;
    }

}
