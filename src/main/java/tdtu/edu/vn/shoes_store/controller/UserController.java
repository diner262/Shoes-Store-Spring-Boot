package tdtu.edu.vn.shoes_store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.dto.UserDto;
import tdtu.edu.vn.shoes_store.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public  ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtoList = userService.getAllUser();
        if(userDtoList != null){
            return  ResponseEntity.ok(userDtoList);
        }
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        System.out.println(token);

        UserDto userDto = userService.findUserByToken(token);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable(name = "id") Long id) {
        UserDto userDto = userService.findUserByID(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        UserDto updatedUser = userService.updateUserByID(id, userDto);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @PostMapping("")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        Map<String, Object> result = new HashMap<>();
        userService.addUser(userDto);

        result.put("statusCode", HttpStatus.OK.value());
        result.put("message", "Create new user successfully!");
        result.put("content", userDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        if(!userService.deleteUserByID(id)){
            result.put("statusCode", HttpStatus.NO_CONTENT.value());
            result.put("message", "Delete user fail!");
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }else {
            result.put("statusCode", HttpStatus.OK.value());
            result.put("message", "Delete user successfully!");
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUserByID(id, userDto);
        Map<String, Object> result = new HashMap<>();
        if (updatedUser != null) {
            result.put("statusCode", HttpStatus.OK.value());
            result.put("message","Update user successfully!");
            result.put("content",updatedUser);
            return  new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/profile")
    public ResponseEntity<?> updateUserByToken(HttpServletRequest request, @RequestBody UserDto userDto) {

        String token = request.getHeader("Authorization").substring(7);
        UserDto updatedUser = userService.updateUserByToken(token, userDto);
        Map<String, Object> result = new HashMap<>();
        if (updatedUser != null) {
            result.put("statusCode", HttpStatus.OK.value());
            result.put("message","Update user successfully!");
            result.put("content",updatedUser);
            return  new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
