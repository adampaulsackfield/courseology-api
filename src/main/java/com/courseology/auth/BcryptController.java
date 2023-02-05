package com.courseology.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class BcryptController {
    PasswordEncoder passwordEncoder;

    @Autowired
    public BcryptController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/encode")
//    public ResponseEntity<String> hashPassword(@RequestHeader String password) {
//        return new ResponseEntity<>(passwordEncoder.encode(password), HttpStatus.OK);
//    }

//    @GetMapping("/matches")
//    public ResponseEntity<Boolean> passwordMatch(@RequestBody BCryptDomain bCryptDomain) {
//        return new ResponseEntity<>(passwordEncoder.matches(bCryptDomain.getPassword(), bCryptDomain.getPasswordEncoded()), HttpStatus.OK);
//    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String password, String hash) {
        return passwordEncoder.matches(password,hash);
    }
}
