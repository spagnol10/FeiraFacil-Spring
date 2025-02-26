package wesp.company.feirafacilapi.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.domain.enums.EnumUserType;
import wesp.company.feirafacilapi.dto.LoginAuthDTO;
import wesp.company.feirafacilapi.dto.LoginResponseDTO;
import wesp.company.feirafacilapi.dto.RegisterAuthDTO;
import wesp.company.feirafacilapi.infra.security.TokenService;
import wesp.company.feirafacilapi.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthContoller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginAuthDTO loginAuthDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginAuthDTO.email(), loginAuthDTO.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.createToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterAuthDTO registerAuthDTO) {
        if (this.userRepository.findUserByEmail(registerAuthDTO.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerAuthDTO.password());

        User newUser = new User(registerAuthDTO.email(), encryptedPassword, registerAuthDTO.role());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
