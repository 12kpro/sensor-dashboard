package capstone.fe_api.users.services;


import capstone.fe_api.exceptions.BadRequestException;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.payloads.UserCreatePayload;
import capstone.fe_api.users.repositories.RoleRepository;
import capstone.fe_api.users.repositories.UserRepository;
import capstone.fe_api.users.Role;
import capstone.fe_api.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepository;

    public User create(UserCreatePayload u) {
        userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });

        Role roleDefault = roleRepository.findByName("USER").orElseThrow(() -> new NotFoundException("Ruolo USER non esiste!!"));
        User newUser = new User(u.getSurname(),u.getEmail(),u.getName(),u.getPassword(), u.getUsername());
        newUser.getRoles().add(roleDefault);

        return userRepo.save(newUser);
    }

    public Page<User> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return userRepo.findAll(pageable);
    }

    public User findById(UUID id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Utete con Id:" + id + "non trovato!!"));
    }

    public User findByEmail(String email) throws NotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utete con email:" + email + "non trovato!!"));
    }
    public User findByUserName(String username) throws NotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Utete:" + username + " non trovato!!"));
    }

    public User findByIdAndUpdate(UUID id, UserCreatePayload u) throws NotFoundException {
        User found = this.findById(id);

        found.setId(id);
        found.setName(u.getName());
        found.setSurname(u.getSurname());
        found.setEmail(u.getEmail());
        found.setPassword(u.getPassword());

        return userRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        User found = this.findById(id);
        userRepo.delete(found);
    }

}
