package tdtu.edu.vn.shoes_store.service;

import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.dto.UserDto;

@Service
public interface UserService {
    void registerUser(UserDto userDto);
}
