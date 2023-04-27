package tdtu.edu.vn.shoes_store.service;

import org.springframework.security.core.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.dto.UserDto;
import tdtu.edu.vn.shoes_store.model.Role;
import tdtu.edu.vn.shoes_store.model.User;
import tdtu.edu.vn.shoes_store.repository.RoleRepository;
import tdtu.edu.vn.shoes_store.repository.UserRepository;

import java.util.Optional;

@Service
@Transient
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(UserDto userToRegister) {
        User user = new User();
        user.setUsername(userToRegister.getUsername());
        user.setEmail(userToRegister.getEmail());
        user.setPassword(passwordEncoder.encode(userToRegister.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRole(role);

        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto findUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setId(user.get().getId());
            userDto.setAddress(user.get().getAddress());
            userDto.setAge(user.get().getAge());
            userDto.setEmail(user.get().getEmail());
            userDto.setGender(user.get().getGender());
            userDto.setPassword(user.get().getPassword());
            userDto.setPhone(user.get().getPhone());
            userDto.setUsername(user.get().getUsername());
            userDto.setRole(user.get().getRole().getName());
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto updateUserByID(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAddress(userDto.getAddress());
            user.setAge(userDto.getAge());
            user.setEmail(userDto.getEmail());
            user.setGender(userDto.getGender());
//            user.setPassword(userDto.getPassword());
            user.setPhone(userDto.getPhone());
//            user.setUsername(userDto.getUsername());
            Role role = roleRepository.findByName(userDto.getRole());
            user.setRole(role);
            User updatedUser = userRepository.save(user);
            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setId(updatedUser.getId());
            updatedUserDto.setAddress(updatedUser.getAddress());
            updatedUserDto.setAge(updatedUser.getAge());
            updatedUserDto.setEmail(updatedUser.getEmail());
            updatedUserDto.setGender(updatedUser.getGender());
//            updatedUserDto.setPassword(updatedUser.getPassword());
            updatedUserDto.setPhone(updatedUser.getPhone());
//            updatedUserDto.setUsername(updatedUser.getUsername());
            updatedUserDto.setRole(updatedUser.getRole().getName());
            return updatedUserDto;
        }
        return null;
    }


}
