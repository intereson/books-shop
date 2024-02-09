package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.RoleRequest;
import by.intereson.ebookservice.dto.response.RoleDTO;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.mappers.RoleMapper;
import by.intereson.ebookservice.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class RoleController {
    private final RoleService roleService;

    @DeleteMapping("role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok("Role deleted successfully!.");
    }

    @PutMapping("role/{id}/update")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        RoleDTO roleUpdate = roleService.updateRole(id, roleDetails);
        return new ResponseEntity<>(roleUpdate, HttpStatus.OK);
    }

    @GetMapping("role/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        RoleDTO roleDTO = roleService.getRoleById(id);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping("role/{name}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable String name) {
        Role role = roleService.getRoleByName(name);
        RoleDTO roleDTO = RoleMapper.INSTANCE.mapToDTO(role);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping("role")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("role")
    public ResponseEntity<String> saveRole(@RequestBody RoleRequest request) {
        roleService.saveRole(request);
        return ResponseEntity.ok("Done");
    }
}
