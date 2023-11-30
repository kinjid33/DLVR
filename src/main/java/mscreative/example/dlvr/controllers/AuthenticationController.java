package mscreative.example.dlvr.controllers;

import lombok.RequiredArgsConstructor;
import mscreative.example.dlvr.dto.request.AuthenticationRequest;
import mscreative.example.dlvr.dto.request.RegisterRequest;
import mscreative.example.dlvr.dto.response.AuthenticationResponse;
import mscreative.example.dlvr.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController
{
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register
            (
                    @RequestBody RegisterRequest registerRequest
            )
    {
        return  ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register
            (
                    @RequestBody AuthenticationRequest authenticationRequest
            )
    {
        return  ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}