package org.example.javahw05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void add(User user){
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

}
