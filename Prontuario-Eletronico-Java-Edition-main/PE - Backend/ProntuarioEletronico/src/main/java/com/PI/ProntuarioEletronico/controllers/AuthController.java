package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.resources.dtos.authentication.AuthenticatedUser;
import com.PI.ProntuarioEletronico.resources.dtos.authentication.LoginUser;
import com.PI.ProntuarioEletronico.services.securityServices.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<AuthenticatedUser> login(@RequestBody LoginUser login){

        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        UserModel user = (UserModel) auth.getPrincipal();
        user.setPassword("");

        AuthenticatedUser authenticated = new AuthenticatedUser(user, token);

        return ResponseEntity.status(HttpStatus.OK).body(authenticated);
    }
}
