package capstone.fe_api.users.controllers;


import capstone.fe_api.auth.JWTTools;
import capstone.fe_api.auth.payloads.AuthenticationSuccessfullPayload;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.User;
import capstone.fe_api.users.payloads.CredentialChangePayload;
import capstone.fe_api.users.payloads.UserUpdatePayload;
import capstone.fe_api.users.services.UserService;
import capstone.fe_api.users.payloads.UserCreatePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
@Slf4j

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;
    @GetMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return userService.find(page, size, sortBy);
    }
    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) throws NotFoundException {
        User userDetails = (User) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        return userService.findById(userId);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) throws Exception {
        return userService.findById(userId);
    }
    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated UserCreatePayload body) {
        body.setPassword(bcrypt.encode(body.getPassword()));
        return userService.create(body);
    }
//    @PostMapping("")
//    @PostAuthorize("hasAuthority('ADMIN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User saveUser(@RequestBody @Validated UserCreatePayload body) {
//        return utenteService.create(body);
//    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody UserUpdatePayload body) throws Exception {
      log.info(body.toString());
        return userService.findByIdAndUpdate(userId, body);
    }
    @PutMapping("/{userId}/change")
    public ResponseEntity<AuthenticationSuccessfullPayload> changeUserCredential(@PathVariable UUID userId, @RequestBody CredentialChangePayload body) throws Exception {
        body.setPassword(bcrypt.encode(body.getPassword()));
        User updatedUser = userService.findByIdAndUpdateCredential(userId, body);

        String token = JWTTools.createToken(updatedUser);

        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        userService.findByIdAndDelete(userId);
    }

}
