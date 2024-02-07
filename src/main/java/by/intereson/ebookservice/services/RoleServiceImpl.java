package by.intereson.ebookservice.services;

import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRole(String name) {
        return roleRepository.readRoleByName(name);
    }
}
