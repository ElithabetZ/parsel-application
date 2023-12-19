package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.UserConverter;
import com.pingwit.parsel.api.dto.UserRequestDto;
import com.pingwit.parsel.api.dto.UserResponseDto;
import com.pingwit.parsel.entity.User;
import com.pingwit.parsel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        ResponseEntity<UserResponseDto> response;
        User saved = userService.save(userConverter.toModel(requestDto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(userConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto requestDto) {
        User updated = userService.update(userConverter.toModel(requestDto));
        return new ResponseEntity<>(userConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        return userService.findById(id)
                .map(result -> new ResponseEntity<>(userConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }
}
