package info.thecodinglive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.EatingPlanData;
import info.thecodinglive.model.SleepData;

@Repository
public interface EatingPlanRepository extends JpaRepository<EatingPlanData, Long> {

	//@Query(value ="SELECT eid,e_content,selectMeal,user_username,date_format(createDate,'%Y%m%d') AS createDate,sum(kcal) AS kcal FROM eatingplandata WHERE user_username=123 GROUP BY createDate")
	//List<EatingPlanData> eatList();
	
}
