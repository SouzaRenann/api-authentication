package com.souza.auth.project_spring.controller;
import com.souza.auth.project_spring.domain.user.AuthenticationDTO;
import com.souza.auth.project_spring.domain.user.LoginResponseDTO;
import com.souza.auth.project_spring.domain.user.RegisterDTO;
import com.souza.auth.project_spring.domain.user.User;
import com.souza.auth.project_spring.repositories.UserRepository;
import com.souza.auth.project_spring.repositories.security.TokenService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.genarateToken((User) auth.getPrincipal());

        return  ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newuser);

        return ResponseEntity.ok().build();
    }

}
