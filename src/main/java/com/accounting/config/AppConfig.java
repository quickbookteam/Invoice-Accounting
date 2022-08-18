package com.accounting.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.accounting.filter.JwtFilter;
import com.accounting.service.Implimentation.UserService;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;
  @Autowired
  private JwtFilter jwtFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService);
  }


  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception{
      return super.authenticationManagerBean();
  }

@Override
protected void configure(HttpSecurity http) throws Exception {
  http
          .csrf().disable()

          .authorizeRequests()
          .antMatchers("/authenticate").permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
@Bean
public BCryptPasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder(10);
}
}

