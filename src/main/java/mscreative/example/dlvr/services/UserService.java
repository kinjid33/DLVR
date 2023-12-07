package mscreative.example.dlvr.services;

import mscreative.example.dlvr.dto.request.ChangePasswordRequest;

import java.security.Principal;

public interface UserService
{
    void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser);
}
