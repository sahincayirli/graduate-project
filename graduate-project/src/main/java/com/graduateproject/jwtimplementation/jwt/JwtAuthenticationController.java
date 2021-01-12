package com.graduateproject.jwtimplementation.jwt;

import com.graduateproject.jwtimplementation.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/loginUser")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword())
            );
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException("User can be locked or not found");
        }

        final var userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtProvider.generateToken(userDetails);
        String role = userDetails.getAuthorities().stream().map(Object::toString).toArray(String[]::new)[0];
        return ResponseEntity.ok(new JwtResponse(token,role));
    }

}
