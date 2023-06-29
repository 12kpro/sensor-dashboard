package capstone.fe_api.utenti.controllers;

import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.utenti.services.RuoloService;
import capstone.fe_api.utenti.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ruoli")
@PreAuthorize("hasAuthority('ADMIN')")

public class RoleController {

    @Autowired
    private RuoloService ruoloService;

    @GetMapping("")
    public Page<Role> getRuoli(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return ruoloService.find(page, size, sortBy);
    }
    @GetMapping("/{ruoloId}")
    public Role getRuolo(@PathVariable UUID userId) throws Exception {
        return ruoloService.findById(userId);
    }

    @PostMapping("/ruoloNome")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRuolo(@PathVariable String ruoloNome) {
        return ruoloService.create(ruoloNome);
    }

    @PutMapping("/{ruoloId}")
    public Role updateRuolo(@PathVariable UUID ruoloId, @RequestBody Role body) throws Exception {
        return ruoloService.findByIdAndUpdate(ruoloId, body);
    }

    @DeleteMapping("/{ruoloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRuolo(@PathVariable UUID ruoloId) throws NotFoundException {
        ruoloService.findByIdAndDelete(ruoloId);
    }
}
