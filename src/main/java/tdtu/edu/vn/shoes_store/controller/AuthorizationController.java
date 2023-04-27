package tdtu.edu.vn.shoes_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.dto.UserDto;
import tdtu.edu.vn.shoes_store.security.TokenStore;
import tdtu.edu.vn.shoes_store.security.jwt.JwtRequest;
import tdtu.edu.vn.shoes_store.security.jwt.JwtTokenUtil;
import tdtu.edu.vn.shoes_store.service.UserDetailsServiceImpl;
import tdtu.edu.vn.shoes_store.service.UserService;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserDto userDto) {
        Map<String, Object> result = new HashMap<>();

        if (userDto.getEmail() == null) {
            return new ResponseEntity<>("Email is required!", HttpStatus.BAD_REQUEST);
        } else if (userDto.getPassword() == null) {
            return new ResponseEntity<>("Password is required!", HttpStatus.BAD_REQUEST);
        } else if (userDto.getUsername() == null) {
            return new ResponseEntity<>("Username is required!", HttpStatus.BAD_REQUEST);
        } else if (userDto.getGender() == null) {
            return new ResponseEntity<>("Gender is required!", HttpStatus.BAD_REQUEST);
        } else if (userDto.getPhone() == null) {
            return new ResponseEntity<>("Phone is required!", HttpStatus.BAD_REQUEST);
        }

        userService.registerUser(userDto);
        result.put("statusCode", HttpStatus.OK.value());
        result.put("timeStamp", LocalTime.now());
        result.put("message", "Register account successfully!");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("Your account has been disabled. Please contact the system administrator for assistance.", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid email or password.", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> content = new HashMap<>();

        if (authenticationRequest.getEmail() == null) {
            return new ResponseEntity<>("Email is required!", HttpStatus.BAD_REQUEST);
        } else if (authenticationRequest.getPassword() == null) {
            return new ResponseEntity<>("Password is required!", HttpStatus.BAD_REQUEST);
        }

        try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            tokenStore.storeToken(token);

            content.put("email", userDetails.getUsername());
            content.put("accessToken", token);

            result.put("statusCode", HttpStatus.OK.value());
            result.put("timeStamp", LocalTime.now());
            result.put("message", "Login successfully!");
            result.put("content", content);
        } catch (Exception e) {
            result.put("statusCode", HttpStatus.UNAUTHORIZED.value());
            result.put("timeStamp", LocalTime.now());
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logoutUser(@RequestHeader("Authorization") String authToken) {
        Map<String, Object> result = new HashMap<>();
        String token = authToken.replace("Bearer ", "");
        tokenStore.removeToken(token);

        result.put("statusCode", HttpStatus.OK.value());
        result.put("timeStamp", LocalTime.now());
        result.put("message", "Logged out successfully!");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
