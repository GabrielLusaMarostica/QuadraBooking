package com.socrates.quadra_booking_api.Service;

import com.socrates.quadra_booking_api.DTO.AuthResponseDTO;
import com.socrates.quadra_booking_api.DTO.LoginRequestDTO;
import com.socrates.quadra_booking_api.DTO.RegisterRequestDTO;
import com.socrates.quadra_booking_api.Model.Usuario;
import com.socrates.quadra_booking_api.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com esse email");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha())); // nunca salva a senha crua
        usuario.setRole(Usuario.Role.USER);

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        // Isso aciona o AuthenticationProvider (DaoAuthenticationProvider) por baixo dos panos:
        // busca o usuário via UserDetailsService e compara a senha via PasswordEncoder.
        // Se não bater, lança BadCredentialsException automaticamente.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        String token = jwtService.generateToken(usuario);
        return new AuthResponseDTO(token);
    }
}