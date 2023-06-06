package com.example.post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import com.example.post.entities.User;

@Repository
@RestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
