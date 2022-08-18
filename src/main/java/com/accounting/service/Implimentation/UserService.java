package com.accounting.service.Implimentation;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accounting.entity.User;
import com.accounting.repositery.UserRepository;
import com.accounting.util.UtilConstants;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
@Service
@Slf4j
public class UserService implements UserDetailsService {
    
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
    	this.userRepository=userRepository;
	}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        if(user==null)
        {
        	log.info("Invalid User");
            throw new UsernameNotFoundException(UtilConstants.INVALID_ADMIN);
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }

}

