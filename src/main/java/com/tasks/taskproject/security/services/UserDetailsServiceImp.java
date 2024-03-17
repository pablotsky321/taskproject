package com.tasks.taskproject.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tasks.taskproject.security.entities.UserEntity;
import com.tasks.taskproject.security.repositories.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException(username+" not found");
        }
        return User
                .builder()
                .username(userOptional.get().getUsername())
                .password(userOptional.get().getPassword())
                .roles(userOptional.get().getRole().name())
                .authorities(userOptional.get().getAuthorities())
                .build();
    }

}
