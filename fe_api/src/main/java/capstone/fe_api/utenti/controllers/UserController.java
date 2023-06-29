package capstone.fe_api.utenti.controllers;


import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.utenti.User;
import capstone.fe_api.utenti.services.UtenteService;
import capstone.fe_api.utenti.payloads.UserCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")


public class UserController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.find(page, size, sortBy);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) throws Exception {
        return utenteService.findById(userId);
    }

    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserCreatePayload body) {
        return utenteService.create(body);
    }

    @PutMapping("/{userId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable UUID userId, @RequestBody User body) throws Exception {
        return utenteService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        utenteService.findByIdAndDelete(userId);
    }

}
