package com.example.post.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.post.dto.PostDTO;
import com.example.post.entities.Post;
import com.example.post.entities.User;
import com.example.post.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{userId}/posts")
    public void addPostToUser(@PathVariable Long userId, @RequestBody PostDTO postDTO) {
    	try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new UserPrincipalNotFoundException("User not found with ID: " + userId);
            }
            
            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            // Set other properties as needed
            
            post.setUser(user);
            userService.addPostToUser(userId, post);
        } catch (UserPrincipalNotFoundException ex) {
            // Handle the case when the user is not found
            // Return an appropriate HTTP response (e.g., 404 Not Found)
        }
    }
        
}
