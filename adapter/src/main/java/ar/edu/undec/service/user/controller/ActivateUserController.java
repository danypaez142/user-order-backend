package ar.edu.undec.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.input.ActivateUserInput;
import user.input.UserDTO;

@RestController
@RequestMapping("users/activate")
public class ActivateUserController {
    ActivateUserInput input;

    @Autowired
    public ActivateUserController(ActivateUserInput input) {
        this.input = input;
    }

    @PutMapping
    public ResponseEntity<?> activateUser(@RequestBody UserDTO dto) {
        try{
            Boolean seActivo = input.activateUser(dto);
            if(seActivo){
                return ResponseEntity.ok().body("User Activated");
            }else{
                return ResponseEntity.badRequest().body("Activation Time Expired for User");
            }
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
