package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class RoleController {
    private final RoleService roleService;

    /**
     * Create new user role
     */
    @PostMapping(ROLES_URL)
    @ResponseStatus(CREATED)
    public RoleResponse createRole(@RequestBody @Valid CreateAndUpdateRoleRequest request) {
        return roleService.createRole(request);
    }

    /**
     * Get a user role by id
     */
    @GetMapping(ROLES_ID_URL)
    @ResponseStatus
    public RoleResponse getRole(@PathVariable Long id) {
        return roleService.getRoleResponse(id);
    }

    /**
     * Get all user roles by id
     */
    @GetMapping(ROLES_URL)
    @ResponseStatus
    public List<RoleResponse> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Update a user role by id
     */
    @PutMapping(ROLES_ID_URL)
    @ResponseStatus
    public RoleResponse updateRole(@PathVariable Long id, @RequestBody @Valid CreateAndUpdateRoleRequest request) {
        return roleService.updateRole(id, request);
    }

    /**
     * Delete a user role by id
     */
    @DeleteMapping(ROLES_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
