package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public User saveUser(CreateUserRequest request) {
        User user = userMapper.mapToEntity(request);
        Role role = roleService.getRoleByName("USER");
        user.setRoleList(new ArrayList<>());
//        user.setShoppingCart(new ShoppingCart());
        user.setOrders(new ArrayList<>());
        user.setLikedBooks(new ArrayList<>());
//        user.getShoppingCart().setBooksByShoppingCart(new ArrayList<>());
        user.getRoleList().add(0, role);
        return userRepository.save(user);
    }
}
