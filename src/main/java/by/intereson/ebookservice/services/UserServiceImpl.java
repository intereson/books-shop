package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateBookLikeRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.transaction.annotation.Isolation.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListMapper userListMapper;
    private final RoleService roleService;
    private final BookService bookService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public UserResponse createUser(CreateUserRequest request) {
        User user = userMapper.mapToEntity(request);
        Role role = roleService.getRoleByRoleName(ROLE_NEW_USER);
        user.setRoleList(new ArrayList<>());
        user.getRoleList().add(START_INTEGER_NULL_INDEX, role);
        user.setLikedBooks(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setShoppingCart(new ShoppingCart());
        user.getShoppingCart().setDetails(new ArrayList<>());
        user.getShoppingCart().setSumPrice(START_SUM_PRICE);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));
    }

    @Override
    public UserResponse getUserResponse(Long userId) {
        User user = getUser(userId);
        return userMapper.mapToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return userListMapper.mapListToDto(users);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public UserResponse updateUser(Long userId, CreateUserRequest request) {
        User newUser = userMapper.mapToEntity(request);
        User oldUser = getUser(userId);
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPassword(newUser.getPassword());
        userRepository.save(oldUser);
        return userMapper.mapToDto(oldUser);
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public UserResponse updateBookLike(Long userId, UpdateBookLikeRequest request) {
        User user = getUser(userId);
        long count = user.getLikedBooks().stream()
                .filter((p) -> p.getId().equals(request.getBookId()))
                .count();
        if (count == START_INTEGER_NULL_INDEX) {
            user.getLikedBooks().add(bookService.getBook(request.getBookId()));
        } else {
            user.getLikedBooks().remove(bookService.getBook(request.getBookId()));
        }
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteUser(Long userId) {
        User user = getUser(userId);
        orderService.updateOrdersColumnUserId(user.getOrders());
        orderDetailService.deleteOrderDetailsFromShoppingCart(user.getShoppingCart().getId());
        userRepository.deleteById(userId);
    }
}
