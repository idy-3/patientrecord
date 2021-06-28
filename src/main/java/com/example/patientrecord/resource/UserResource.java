package com.example.patientrecord.resource;

import com.example.patientrecord.model.User;
import com.example.patientrecord.model.UserRole;
import com.example.patientrecord.service.UserService;
import com.example.patientrecord.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;


@RestController
@RequestMapping("api/v1")
public class UserResource {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    Logger log = Logger.getLogger(UserResource.class.getName());

    @Autowired
    public UserResource(UserService userService, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest ){
        log.severe("register here.");
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setUsername(registrationRequest.getUsername());
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public UserResponse auth(@RequestBody UserRequest request){
        User user = userService.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        String token = jwtTokenProvider.generateToken(user.getUsername());
        return new UserResponse(token);
    }

    @PostMapping("/user/user")
    public String userRoles(@RequestBody Map<String, String> request){
        String username = request.get("username");
        UserRole userRole = userService.findByUsername(username).getUserRole();
        return String.valueOf(userRole.getId());
    }
}
