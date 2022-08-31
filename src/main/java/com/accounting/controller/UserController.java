package com.accounting.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.modal.jwt_modals.JwtRequest;
import com.accounting.modal.jwt_modals.JwtResponse;
import com.accounting.service.Implimentation.UserService;
import com.accounting.util.JWTUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	private JWTUtility jwtUtility;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    
	@Autowired
	public UserController(JWTUtility jwtUtility,AuthenticationManager authenticationManager,UserService userService)
	{
		this.authenticationManager=authenticationManager;
		this.jwtUtility=jwtUtility;
		this.userService=userService;
	}
    
  
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            e.printStackTrace();
            log.info(e.getMessage());
            throw new Exception("Invalid Credentials",e);
        }
        final UserDetails userDetails=
                        userService.loadUserByUsername(jwtRequest.getUsername()) ;
                final String token=jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);

    }
}
