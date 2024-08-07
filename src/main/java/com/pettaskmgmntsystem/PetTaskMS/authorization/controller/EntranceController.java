package com.pettaskmgmntsystem.PetTaskMS.authorization.controller;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.LoginForm;
import com.pettaskmgmntsystem.PetTaskMS.authorization.service.MyUserDetailService;
import com.pettaskmgmntsystem.PetTaskMS.authorization.service.webtoken.JwtService;
import com.pettaskmgmntsystem.PetTaskMS.authorization.service.UserService;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("entry/v2")
@Slf4j
public class EntranceController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;


    @PostMapping("/registration")
    public ResponseEntity<CustomUsersDto> createUsers(@RequestBody CustomUsersDto customUsersDto) {
        CustomUsersDto customUsersDtoLocal = userService.createUser(customUsersDto);
        if (customUsersDtoLocal != null) {
            return ResponseEntity.ok(customUsersDtoLocal);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/authorization")
    public ResponseEntity<String> authorizationUser(@RequestBody LoginForm loginForm) throws UsernameNotFoundException {
        log.info("Метод авторизации, POST ");
        String jwtToken = userService.authorizationUser(loginForm);
        if (jwtToken != null) {
            return ResponseEntity.ok(jwtToken);
        }
        return ResponseEntity.badRequest().build();

    }
}
