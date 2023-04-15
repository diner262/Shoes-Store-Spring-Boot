package tdtu.edu.vn.shoes_store.service;

import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.dto.UserDto;
import tdtu.edu.vn.shoes_store.model.User;

@Service
public interface UserService {
    void registerUser(UserDto userDto);
    User findUserByEmail(String email);
}
