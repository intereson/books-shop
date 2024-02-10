package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.UserListMapper;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListMapper userListMapper;
    private final RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userListMapper.toDTOList(users);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserResponse saveUser(CreateUserRequest request) {
        User user = userMapper.mapToEntity(request);//todo
        Role role = roleService.getRoleByName("USER");
        user.setRoleList(new ArrayList<>());
        user.getRoleList().add(0, role);
        user.setLikedBooks(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setShoppingCart(new ShoppingCart());
        user.getShoppingCart().setParts(new ArrayList<>());
        userRepository.save(user);
        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserResponse updateUser(Long id, CreateUserRequest request) {
        User newUser = userMapper.mapToEntity(request);
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPassword(newUser.getPassword());
        userRepository.save(oldUser);
        return userMapper.mapToDTO(oldUser);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
