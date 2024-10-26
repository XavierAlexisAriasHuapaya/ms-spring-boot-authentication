package dev.arias.huapaya.ms_authentication.service.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.arias.huapaya.ms_authentication.persistence.entity.UserEntity;
import dev.arias.huapaya.ms_authentication.persistence.repository.UserRepository;
import dev.arias.huapaya.ms_authentication.presentation.dto.AuthenticationRequestDTO;
import dev.arias.huapaya.ms_authentication.presentation.dto.AuthenticationResponseDTO;
import dev.arias.huapaya.ms_authentication.presentation.dto.TokenDTO;

import java.util.Map;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final String USER_EXCEPTION_MSG = "Error the authentication";

    private Map<String, Object> extraClaims(UserEntity userEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userEntity.getAuthorities());
        return claims;
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        AuthenticationResponseDTO response = new AuthenticationResponseDTO();
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());
        this.authenticationManager.authenticate(authentication);
        UserEntity user = this.userRepository.findByUsername(request.getUsername());
        log.info(user.getPassword());
        response.setJwt(jwtService.generateToken(user, this.extraClaims(user)));
        return response;
    }

    public TokenDTO validateToken(TokenDTO tokenDTO) {
        try {
            this.jwtService.extractUsername(tokenDTO.getAccessToken());
            return tokenDTO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }
    }

}
