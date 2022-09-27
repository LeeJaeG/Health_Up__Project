package com.healthup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthup.project.model.User;



public interface UserRepository extends JpaRepository<User, Integer>  {

	public User findByUsername(String username);
	
	
}
