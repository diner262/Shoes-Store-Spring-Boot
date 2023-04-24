package tdtu.edu.vn.shoes_store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String phone;

    @Builder
    public UserDto(String username, String password, String email, String gender, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }
}
