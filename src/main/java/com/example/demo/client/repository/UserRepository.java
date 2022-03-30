package com.example.demo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.client.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
