package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class RoleController {
    private final RoleService roleService;

    /**
     * Delete a user role by id
     */
    @DeleteMapping("roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }

    /**
     * Update a user role by id
     */
    @PutMapping("roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse updateRoleById(@PathVariable Long id, @RequestBody @Valid CreateAndUpdateRoleRequest request) {
        return roleService.updateRoleById(id, request);
    }

    /**
     * Get a user role by id
     */
    @GetMapping("roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse getRoleById(@PathVariable Long id) {
        return roleService.getRoleByIdDto(id);
    }

    /**
     * Get all user roles by id
     */
    @GetMapping("roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Create new user role
     */
    @PostMapping("roles")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(@RequestBody @Valid CreateAndUpdateRoleRequest request) {
        return roleService.createRole(request);
    }
}
