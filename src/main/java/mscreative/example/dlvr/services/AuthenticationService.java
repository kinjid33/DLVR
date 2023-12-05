package mscreative.example.dlvr.services;

import mscreative.example.dlvr.dto.request.AuthenticationRequest;
import mscreative.example.dlvr.dto.request.RegisterRequest;
import mscreative.example.dlvr.dto.response.AuthenticationResponse;
import mscreative.example.dlvr.dto.response.RegisterResponse;

public interface AuthenticationService
{
    RegisterResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
