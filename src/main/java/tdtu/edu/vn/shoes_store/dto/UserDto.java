package tdtu.edu.vn.shoes_store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tdtu.edu.vn.shoes_store.model.Role;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String phone;

    private Integer age;
    private String address;
    private String role;

    @Builder
    public UserDto(Long id, String address, int age,String username, String password, String email, String gender, String phone,String role) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.role = role;
    }
}
