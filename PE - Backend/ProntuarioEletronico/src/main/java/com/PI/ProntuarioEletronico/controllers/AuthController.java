package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.resources.dtos.authentication.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("authentication")
    public ResponseEntity auth(LoginUser login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var user = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }
}
