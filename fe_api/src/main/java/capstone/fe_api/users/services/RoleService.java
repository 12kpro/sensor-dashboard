package capstone.fe_api.users.services;

import capstone.fe_api.exceptions.BadRequestException;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.repositories.RoleRepository;
import capstone.fe_api.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role create(Role r) {
        roleRepository.findByName(r.getName()).ifPresent(user -> {
            throw new BadRequestException("Ruolo " + r.getName().toUpperCase() + " già in uso!");
        });
        Role newRole = new Role(r.getName().toUpperCase());
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
        return roleRepository.findByName(r).orElseThrow(() -> new NotFoundException("Ruolo con nome:" + r + "non trovato!!"));
    }
    public Role findByIdAndUpdate(UUID id, Role r) throws NotFoundException {
        Role found = this.findById(id);
        found.setId(id);
        found.setName(r.getName().toUpperCase());
        return roleRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Role found = this.findById(id);
        roleRepository.delete(found);
    }
}
