package info.thecodinglive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.thecodinglive.model.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	public User findByUsername(String username);
	
	
}
