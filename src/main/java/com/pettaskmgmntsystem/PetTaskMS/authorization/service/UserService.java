package com.pettaskmgmntsystem.PetTaskMS.authorization.service;


import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.LoginForm;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.mapper.UserMapperImpl;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.service.webtoken.JwtService;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthorizationRepository authorizationRepository;
    @Autowired
    private UserMapperImpl userMapperImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    public CustomUsersDto createUser (CustomUsersDto customUsersDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!ConstantsClass.ADMINROLE.equals(customUsersDto.getRole()) && !ConstantsClass.USERROLE.equals(customUsersDto.getRole())){
            customUsersDto.setRole(ConstantsClass.USERROLE);
        }
        CustomUsers customUsers = userMapperImpl.convertDtoToUser(customUsersDto);
        customUsers.setPasswordKey(passwordEncoder.encode(customUsers.getPasswordKey()));
        return userMapperImpl.convertUserToDto(
                authorizationRepository.save(customUsers)
        );
    }

    public String authorizationUser(LoginForm loginForm) throws UsernameNotFoundException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(), loginForm.getPasswordKey()
                ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.getEmail()));
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumDescription() + " " + loginForm.getEmail());
        }
    }

}
