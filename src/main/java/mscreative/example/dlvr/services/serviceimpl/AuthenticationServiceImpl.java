package mscreative.example.dlvr.services.serviceimpl;

import lombok.RequiredArgsConstructor;
import mscreative.example.dlvr.dto.request.AuthenticationRequest;
import mscreative.example.dlvr.dto.request.RegisterRequest;
import mscreative.example.dlvr.dto.response.AuthenticationResponse;
import mscreative.example.dlvr.enums.Role;
import mscreative.example.dlvr.enums.TokenType;
import mscreative.example.dlvr.models.Token;
import mscreative.example.dlvr.models.User;
import mscreative.example.dlvr.repositories.TokenRepo;
import mscreative.example.dlvr.repositories.UserRepo;
import mscreative.example.dlvr.services.AuthenticationService;
import mscreative.example.dlvr.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService
{
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;
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
        User savedUser = userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

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

        revokeAllUserTokens(user);

        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user)
    {
        List<Token> validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());

        if(validUserTokens.isEmpty())
        {
            return;
        }
        else
        {
            validUserTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
        }
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken)
    {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepo.save(token);
    }
}