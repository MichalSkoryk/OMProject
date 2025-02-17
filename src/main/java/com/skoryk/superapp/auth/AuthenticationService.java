package com.skoryk.superapp.auth;

import com.skoryk.superapp.model.User;
import com.skoryk.superapp.repository.UserRepository;
import com.skoryk.superapp.service.JWTService;
import com.skoryk.superapp.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String register(RegisterRequest request) {

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.USER);
        user.setGroupCount(0);

        userRepository.save(user);
        return "ok";

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(request.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException(request.getEmail());
            }
            String jwtToken =  jwtService.generateToken(request.getEmail());
            return AuthenticationResponse.builder().token(jwtToken).build();
        }
        return AuthenticationResponse.builder().token("Wrong Password").build();
    }
}
