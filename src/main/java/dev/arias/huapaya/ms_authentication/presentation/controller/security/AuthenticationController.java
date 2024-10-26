package dev.arias.huapaya.ms_authentication.presentation.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.ms_authentication.presentation.dto.AuthenticationRequestDTO;
import dev.arias.huapaya.ms_authentication.presentation.dto.AuthenticationResponseDTO;
import dev.arias.huapaya.ms_authentication.presentation.dto.TokenDTO;
import dev.arias.huapaya.ms_authentication.service.security.AuthenticationService;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@AllArgsConstructor
@RestController
@RequestMapping(path = "security")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "authenticate")
    public ResponseEntity<?> postMethodName(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO response = this.authenticationService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @PostMapping(path = "validate")
    // public ResponseEntity<?> validate(@RequestBody Map<String, String> request) {
    //     Map<String, Object> response = new HashMap<>();
    //     Boolean validate = this.authenticationService.validateToken(request.get("jwt"));
    //     response.put("validate", validate ? "Valid" : "Invalid");
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    @PostMapping(path = "validate")
    public ResponseEntity<TokenDTO> validate(@RequestHeader String accessToken) {
        return ResponseEntity.ok(this.authenticationService.validateToken(TokenDTO.builder().accessToken(accessToken).build()));
    }

}
