package mscreative.example.dlvr.services.serviceimpl;

import lombok.RequiredArgsConstructor;
import mscreative.example.dlvr.dto.request.AuthenticationRequest;
import mscreative.example.dlvr.dto.request.RegisterRequest;
import mscreative.example.dlvr.dto.response.AuthenticationResponse;
import mscreative.example.dlvr.enums.Role;
import mscreative.example.dlvr.models.User;
import mscreative.example.dlvr.repositories.UserRepo;
import mscreative.example.dlvr.services.AuthenticationService;
import mscreative.example.dlvr.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService
{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest)
    {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
    {
        authenticationManager.authenticate
                (
                new UsernamePasswordAuthenticationToken
                        (
                                authenticationRequest.getEmail(),
                                authenticationRequest.getPassword()
                        )
                );
        User user = userRepo.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
