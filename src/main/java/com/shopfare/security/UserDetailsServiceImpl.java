package com.shopfare.security;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setUsername(username);
    applicationUser.setPassword("shopfare");
    return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
  }
}
