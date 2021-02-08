package com.heeju.sharpGong.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {
    private String memberEmail;
    private String memberPassword;
}
