package capstone.fe_api.users.controllers;


import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.User;
import capstone.fe_api.users.services.UserService;
import capstone.fe_api.users.payloads.UserCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")


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
    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) throws Exception {
        return userService.findById(userId);
    }
    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {

        body.setPassword(bcrypt.encode(body.getPassword()));
        User createdUser = userService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
//    @PostMapping("")
//    @PostAuthorize("hasAuthority('ADMIN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User saveUser(@RequestBody @Validated UserCreatePayload body) {
//        return utenteService.create(body);
//    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody UserCreatePayload body) throws Exception {
        body.setPassword(bcrypt.encode(body.getPassword()));
        return userService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        userService.findByIdAndDelete(userId);
    }

}
