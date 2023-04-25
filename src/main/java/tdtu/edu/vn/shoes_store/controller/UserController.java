package tdtu.edu.vn.shoes_store.controller;

import org.json.JSONObject;
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
import tdtu.edu.vn.shoes_store.model.User;
import tdtu.edu.vn.shoes_store.security.TokenStore;
import tdtu.edu.vn.shoes_store.security.jwt.JwtRequest;
import tdtu.edu.vn.shoes_store.security.jwt.JwtResponse;
import tdtu.edu.vn.shoes_store.security.jwt.JwtTokenUtil;
import tdtu.edu.vn.shoes_store.service.UserDetailsServiceImpl;
import tdtu.edu.vn.shoes_store.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final Map<String, Object> result = new HashMap<>();
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
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
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
        return new ResponseEntity<>("Register user successfully!", HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        if (authenticationRequest.getEmail() == null) {
            return new ResponseEntity<>("Email is required!", HttpStatus.BAD_REQUEST);
        } else if (userService.findUserByEmail(authenticationRequest.getEmail()) == null) {
            return new ResponseEntity<>("Email is not found!", HttpStatus.BAD_REQUEST);
        } else if (authenticationRequest.getPassword() == null) {
            return new ResponseEntity<>("Password is required!", HttpStatus.BAD_REQUEST);
        }

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        tokenStore.storeToken(token);

        result.put("status", HttpStatus.OK);
        result.put("timeStamp", LocalTime.now());
        result.put("message", "Login success!");
        result.put("content", userDetails);
        result.put("accessToken", new JwtResponse(token));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String authToken) {
        String token = authToken.replace("Bearer ", "");
        tokenStore.removeToken(token);
        return ResponseEntity.ok().body("Logged out successfully");
    }

}
