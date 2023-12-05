package mscreative.example.dlvr.services.serviceimpl;

import lombok.RequiredArgsConstructor;
import mscreative.example.dlvr.dto.request.ChangePasswordRequest;
import mscreative.example.dlvr.models.User;
import mscreative.example.dlvr.repositories.UserRepo;
import mscreative.example.dlvr.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser)
    {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

//        check if current password is correct
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword()))
        {
            throw new IllegalStateException("Wrong password");
        }
//        check if new password matches confirm password
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword()))
        {
            throw new IllegalStateException("Passwords are not the same");
        }
//        update the password
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
//        save the new password
        userRepo.save(user);
    }
}