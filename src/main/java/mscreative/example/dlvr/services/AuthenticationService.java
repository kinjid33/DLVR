package mscreative.example.dlvr.services;

import mscreative.example.dlvr.dto.request.AuthenticationRequest;
import mscreative.example.dlvr.dto.request.RegisterRequest;
import mscreative.example.dlvr.dto.response.AuthenticationResponse;

public interface AuthenticationService
{
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
