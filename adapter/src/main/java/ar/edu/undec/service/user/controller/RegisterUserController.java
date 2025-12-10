package ar.edu.undec.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.input.RegisterUserInput;
import user.input.UserDTO;

@RestController
@RequestMapping("users")
public class RegisterUserController {
    RegisterUserInput input;

    @Autowired
    public RegisterUserController(RegisterUserInput input) {
        this.input = input;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try{
            userDTO = input.registerUser(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
