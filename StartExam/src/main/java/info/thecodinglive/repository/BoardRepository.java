package info.thecodinglive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.thecodinglive.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
