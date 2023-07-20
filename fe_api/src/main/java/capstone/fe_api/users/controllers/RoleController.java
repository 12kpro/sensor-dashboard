package capstone.fe_api.users.controllers;

import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.users.services.RoleService;
import capstone.fe_api.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")

public class RoleController {

    @Autowired
    private RoleService roleService;
// per i ruoli paginati
//    @GetMapping("")
//    public Page<Role> getRuoli(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
//                               @RequestParam(defaultValue = "id") String sortBy) {
//        return roleService.find(page, size, sortBy);
//    }
    @GetMapping("")
    public List<Role> getRuoli() {
        return roleService.find();
    }
    @GetMapping("/{ruoloId}")
    public Role getRuolo(@PathVariable UUID ruoloId) throws Exception {
        return roleService.findById(ruoloId);
    }

    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRuolo(@RequestBody Role body) {
        return roleService.create(body);
    }

    @PutMapping("/{ruoloId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Role updateRuolo(@PathVariable UUID ruoloId, @RequestBody Role body) throws Exception {
        return roleService.findByIdAndUpdate(ruoloId, body);
    }

    @DeleteMapping("/{ruoloId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRuolo(@PathVariable UUID ruoloId) throws NotFoundException {
        roleService.findByIdAndDelete(ruoloId);
    }
}
