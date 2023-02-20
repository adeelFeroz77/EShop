package com.EShop.EShop.service;

import com.EShop.EShop.domain.User;
import com.EShop.EShop.dto.UserDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    UserService(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        return toDto(findById(id));
    }

    public UserDto saveUser(UserDto userDto){
        User u = toDo(userDto);
        Optional<User> user = userRepository.findUserByEmail(u.getEmail());
        if(user.isEmpty()){
            return toDto(userRepository.save(u));
        } else if (!user.get().getIsActive()) {
            user.get().setIsActive(Boolean.TRUE);
            return toDto(userRepository.save(user.get()));
        }
        throw new RecordAlreadyExistException("User Already exist with same email address");
    }

    public UserDto inActiveUser(Long id){
        User u = findById(id);
        u.setIsActive(Boolean.FALSE);
        return toDto(userRepository.save(u));
    }

    public UserDto updateUserFields(Long id, Map<String,Object> fields){
        User u = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(User.class,key);
            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,u,value);
        });
        return toDto(userRepository.save(u));
    }

    private User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                ()->new RecordNotFoundException(String.format("User not found on id: %d",id))
        );
    }

    private User toDo(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    private UserDto toDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
