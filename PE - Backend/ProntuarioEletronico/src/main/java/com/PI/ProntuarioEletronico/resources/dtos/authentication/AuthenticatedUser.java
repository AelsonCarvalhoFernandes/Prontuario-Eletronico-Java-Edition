package com.PI.ProntuarioEletronico.resources.dtos.authentication;

import com.PI.ProntuarioEletronico.models.UserModel;

public class AuthenticatedUser {

    private UserModel user;
    private String token;

    public AuthenticatedUser(UserModel user, String token){
        this.user = user;
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
