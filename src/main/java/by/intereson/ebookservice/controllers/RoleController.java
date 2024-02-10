package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.RoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class RoleController {
    private final RoleService roleService;

    @DeleteMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }

    @PutMapping("role/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        return roleService.updateRole(id, roleDetails);
    }

    @GetMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse getRole(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping("role")
    public RoleResponse saveRole(@RequestBody RoleRequest request) {
        return roleService.saveRole(request);
    }
}
