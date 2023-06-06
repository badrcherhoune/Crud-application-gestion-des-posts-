package com.example.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.post.entities.Post;
import com.example.post.entities.User;
import com.example.post.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User"));
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public void addPostToUser(Long userId, Post post)  {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getPosts().add(post);
            post.setUser(user);
            
            userRepository.save(user);
        } else {
            // Handle the case when the user is not found
            //throw new UserNotFoundException("User not found with id: " + userId);
        }
    }


}
