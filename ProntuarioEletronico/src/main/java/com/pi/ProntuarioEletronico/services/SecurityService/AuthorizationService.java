package com.pi.ProntuarioEletronico.services.SecurityService;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IUserRepository;

import ch.qos.logback.classic.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserDetails userDetails = userRepository.findByEmail(username);
        UserDetails user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("\n\n\n\n\nUsuário não encontrado: " + username);
        }

        // return userRepository.findByEmail(username);
        return user;
    }
}
