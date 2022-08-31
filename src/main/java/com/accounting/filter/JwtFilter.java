package com.accounting.filter;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.accounting.service.Implimentation.UserService;
import com.accounting.util.JWTUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter
{
     private JWTUtility jwtUtility;
     private UserService userService;
    
    @Autowired
    public JwtFilter(JWTUtility jwtUtility,UserService userService) {
    	this.jwtUtility=jwtUtility;
    	this.userService=userService;
	}
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization=request.getHeader("Authorization");
        String token=null;
        String userName=null;
        if(authorization!=null && authorization.startsWith("Bearer "))
        {
            token=authorization.substring(7);
            userName=jwtUtility.getUsernameFromToken(token);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = userService.loadUserByUsername(userName);

            //Now we have userDetails and token, so we validate the token
            if (jwtUtility.validateToken(token, userDetails)) {
//If token is valid , so we need to create userNamePasswordToken and add to th security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userService,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}


