package com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses;

import java.util.Optional;

public interface ValidationClass {

    Optional<ValidationClassImpl> validEmailOrId(String line);
}
