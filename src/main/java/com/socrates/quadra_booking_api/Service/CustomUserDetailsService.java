package com.socrates.quadra_booking_api.Service;

import com.socrates.quadra_booking_api.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // esse metodo é o que o spring security vai chamar quando um usuario tentar logar, se o user for encontrado pela query, ele retorna o usuario encontrado que tem getpassword(), entao ele compara com a senha que veio no login do usuario e decide se autentica ou nao
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com email: " + email));
    }

}
