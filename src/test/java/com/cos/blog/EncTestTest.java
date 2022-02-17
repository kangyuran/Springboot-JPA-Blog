package com.cos.blog;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class EncTestTest {

    @Test
    public void hash_Enc(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 Hash : " + encPassword);
    }
}