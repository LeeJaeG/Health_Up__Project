package info.thecodinglive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.SleepData;

@Repository
public interface SleepDataRepository extends JpaRepository<SleepData, Long> {

}
