package com.pettaskmgmntsystem.PetTaskMS;

import com.pettaskmgmntsystem.PetTaskMS.authorization.UserService;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("registration/v2")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseEntity<CustomUsersDto> createCustomUsers(@RequestBody CustomUsersDto customUsersDto){
        CustomUsersDto finalCustomUsersDto = userService.createUser(customUsersDto);
        if(finalCustomUsersDto != null){
            return ResponseEntity.ok(finalCustomUsersDto);
        }
        return ResponseEntity.internalServerError().build();
    }

}
