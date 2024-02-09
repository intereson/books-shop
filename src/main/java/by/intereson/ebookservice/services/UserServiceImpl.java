package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public User saveUser(CreateUserRequest request) {
        User user = userMapper.mapToEntity(request);//todo
        Role role = roleService.getRoleByName("USER");
        user.setRoleList(new ArrayList<>());
        user.getRoleList().add(0, role);
        user.setLikedBooks(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setShoppingCart(new ShoppingCart());
        user.getShoppingCart().setBooksByShoppingCart(new ArrayList<>());
        return userRepository.save(user);
    }
}
