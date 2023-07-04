package capstone.fe_api.users.controllers;

import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.services.RoleService;
import capstone.fe_api.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMIN')")

public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public Page<Role> getRuoli(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return roleService.find(page, size, sortBy);
    }
    @GetMapping("/{ruoloId}")
    public Role getRuolo(@PathVariable UUID ruoloId) throws Exception {
        return roleService.findById(ruoloId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRuolo(@RequestBody Role body) {
        return roleService.create(body);
    }

    @PutMapping("/{ruoloId}")
    public Role updateRuolo(@PathVariable UUID ruoloId, @RequestBody Role body) throws Exception {
        return roleService.findByIdAndUpdate(ruoloId, body);
    }

    @DeleteMapping("/{ruoloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRuolo(@PathVariable UUID ruoloId) throws NotFoundException {
        roleService.findByIdAndDelete(ruoloId);
    }
}
