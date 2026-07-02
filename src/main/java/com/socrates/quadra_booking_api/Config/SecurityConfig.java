package com.socrates.quadra_booking_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    //vai ser chamado pra encriptografar as senhas, ele transforma a senha de texto puro em hash(uma sequencia de caracteres embaralhada e irreversivel). Com o encoder tambem é possivel comparar esse hash embaralhado com uma senha em string e retorna se é de fato a senha valida
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
