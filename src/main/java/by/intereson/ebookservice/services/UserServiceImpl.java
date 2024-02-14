package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
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

import static by.intereson.ebookservice.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListMapper userListMapper;
    private final RoleService roleService;
    private final BookService bookService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserResponse createUser(CreateUserRequest request) {
        User user = userMapper.mapToEntity(request);
        Role role = roleService.getRoleByName(ROLE_NEW_USER);
        user.setRoleList(new ArrayList<>());
        user.getRoleList().add(START_INDEX, role);
        user.setLikedBooks(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setShoppingCart(new ShoppingCart());
        user.getShoppingCart().setParts(new ArrayList<>());
        user.getShoppingCart().setSumPrice(START_SUM_PRICE);
        userRepository.save(user);
        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
    }

    @Override
    public UserResponse getUserByIdDTO(Long id) {
        User user = getUserById(id);
        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userListMapper.toDTOList(users);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserResponse updateUserByIdDTO(Long id, CreateUserRequest request) {
        User newUser = userMapper.mapToEntity(request);
        User oldUser = getUserById(id);
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPassword(newUser.getPassword());
        userRepository.save(oldUser);
        return userMapper.mapToDTO(oldUser);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserResponse updateLikedBooksByUserIdDTO(Long id, UpdateLikedBooksByUserRequest request) {
        User user = getUserById(id);
        long count = user.getLikedBooks().stream().filter((p) -> p.getId().equals(request.getIdBook())).count();
        if (count == START_INDEX) {
            user.getLikedBooks().add(bookService.getBookById(request.getIdBook()));
        } else {
            user.getLikedBooks().remove(bookService.getBookById(request.getIdBook()));
        }
        userRepository.save(user);
        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
