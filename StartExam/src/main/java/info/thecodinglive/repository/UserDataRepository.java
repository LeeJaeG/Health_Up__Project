package info.thecodinglive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
