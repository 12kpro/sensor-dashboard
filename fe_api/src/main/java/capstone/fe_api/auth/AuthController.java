package capstone.fe_api.auth;


import capstone.fe_api.auth.payloads.AuthenticationSuccessfullPayload;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.exceptions.UnauthorizedException;
import capstone.fe_api.users.User;
import capstone.fe_api.users.payloads.UserLoginPayload;
import capstone.fe_api.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
            throws NotFoundException {

        User user = userService.findByUserName(body.getUsername());

        String plainPW = body.getPassword();
        String hashedPW = user.getPassword();

        if (!bcrypt.matches(plainPW, hashedPW))
            throw new UnauthorizedException("Credenziali non valide");

        String token = JWTTools.createToken(user);
        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
    }

}
