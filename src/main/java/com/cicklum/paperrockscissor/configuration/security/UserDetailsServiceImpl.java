package com.cicklum.paperrockscissor.configuration.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.model.User;
import com.cicklum.paperrockscissor.model.UserDetailsImpl;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AplicationUserService  aplicationUserService;

    public UserDetailsServiceImpl(AplicationUserService aplicationUserService) {this.aplicationUserService = aplicationUserService;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = aplicationUserService.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
