package com.EShop.EShop.controller;

import com.EShop.EShop.dto.UserDto;
import com.EShop.EShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/user")
    private ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/user")
    private ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PatchMapping("/user/{id}")
    private ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody Map<String,Object> f){
        return ResponseEntity.ok(userService.updateUserFields(id,f));
    }

    @DeleteMapping("/user/{id}")
    private ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.inActiveUser(id));
    }

}
