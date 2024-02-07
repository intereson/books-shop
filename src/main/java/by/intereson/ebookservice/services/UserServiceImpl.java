package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.responses.CreateUserResponse;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.mappers.UserMapperResponse;
import by.intereson.ebookservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserMapperResponse userMapperResponse;
    private final RoleService roleService;

    @Override
    public CreateUserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userMapperResponse.mapToUserResponse(user);
    }

    @Override
    public CreateUserResponse saveUser(CreateUserRequest request) {
        User user = userMapper.mapToUser(request);
        user.getRoleList().add(0, roleService.getRole("USER").orElseThrow(RuntimeException::new));
        User saveUser = userRepository.save(user);
        return userMapperResponse.mapToUserResponse(saveUser);

    }
}
