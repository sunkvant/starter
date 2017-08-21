package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by foooox on 16.8.17.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity userEntity = userRepository.findByLogin(username);

        return userRepository.findByLogin(username);



    }
}