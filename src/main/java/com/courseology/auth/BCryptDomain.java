package com.courseology.auth;

import lombok.Data;

@Data
public class BCryptDomain {
    private String password;
    private String passwordEncoded;
}