package capstone.fe_api.utenti.services;


import capstone.fe_api.exceptions.BadRequestException;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.utenti.payloads.UserCreatePayload;
import capstone.fe_api.utenti.repositories.RoleRepository;
import capstone.fe_api.utenti.repositories.UserRepository;
import capstone.fe_api.utenti.Role;
import capstone.fe_api.utenti.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UserRepository utenteRepo;
    @Autowired
    private RoleRepository roleRepository;

    public User create(UserCreatePayload u) {
        utenteRepo.findByEmail(u.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });

        Role roleDefault = roleRepository.findByNome("USER").orElseThrow(() -> new NotFoundException("Ruolo USER non esiste!!"));
        User newUser = new User(u.getCognome(),u.getEmail(),u.getNome(),u.getPassword(), u.getUsername());
        newUser.getRuoli().add(roleDefault);

        return utenteRepo.save(newUser);
    }

    public Page<User> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return utenteRepo.findAll(pageable);
    }

    public User findById(UUID id) throws NotFoundException {
        return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Utete con Id:" + id + "non trovato!!"));
    }

    public User findByEmail(String email) throws NotFoundException {
        return utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utete con email:" + email + "non trovato!!"));
    }
    public User findByUserName(String username) throws NotFoundException {
        return utenteRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Utete:" + username + " non trovato!!"));
    }

    public User findByIdAndUpdate(UUID id, User u) throws NotFoundException {
        User found = this.findById(id);

        found.setId(id);
        found.setNome(u.getNome());
        found.setCognome(u.getCognome());
        found.setEmail(u.getEmail());

        return utenteRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        User found = this.findById(id);
        utenteRepo.delete(found);
    }

}
