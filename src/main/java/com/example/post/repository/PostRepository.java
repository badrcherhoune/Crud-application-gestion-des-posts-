package com.example.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.example.post.entities.Post;

@Repository
@RestResource
public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUserId(Long userId);

}
