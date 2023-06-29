package capstone.fe_api.utenti.services;

import capstone.fe_api.exceptions.BadRequestException;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.utenti.repositories.RoleRepository;
import capstone.fe_api.utenti.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RuoloService {
    @Autowired
    RoleRepository roleRepository;
    public Role create(String r) {
        roleRepository.findByNome(r).ifPresent(user -> {
            throw new BadRequestException("Ruolo " + r + " già in uso!");
        });
        Role newRole = new Role(r);
        return roleRepository.save(newRole);
    }
    public Page<Role> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return roleRepository.findAll(pageable);
    }
    public Role findById(UUID id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Ruolo con Id:" + id + "non trovato!!"));
    }
    public Role findNome(String r) throws NotFoundException {
        return roleRepository.findByNome(r).orElseThrow(() -> new NotFoundException("Ruolo con nome:" + r + "non trovato!!"));
    }
    public Role findByIdAndUpdate(UUID id, Role r) throws NotFoundException {
        Role found = this.findById(id);
        found.setId(id);
        found.setNome(r.getNome());
        return roleRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Role found = this.findById(id);
        roleRepository.delete(found);
    }
}
