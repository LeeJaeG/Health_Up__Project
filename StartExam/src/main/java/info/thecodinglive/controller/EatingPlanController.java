package info.thecodinglive.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.EatingPlanData;
import info.thecodinglive.model.Role;
import info.thecodinglive.model.User;
import info.thecodinglive.repository.BoardRepository;
import info.thecodinglive.repository.EatingPlanRepository;
import info.thecodinglive.repository.UserRepository;
import info.thecodinglive.service.BoardService;
import info.thecodinglive.service.EatingService;



@Controller
@RequestMapping("/eating")
public class EatingPlanController {
	@Autowired
	EatingPlanRepository eatingPlanRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	EatingService eatingService;
	@Autowired
	   EntityManager em;
	   
	   
	@GetMapping("/form")
	public String eatForm() {
	   
	   return "eating/eatingForm";
	   
	}
	
	@GetMapping("/chart")
	public String eatChart(Model model) {
	   
	   Query query=em.createQuery(
	   "select date_format(createDate,'%Y%m%d') AS date,sum(kcal) AS kcal from EatingPlanData WHERE user_username=123 GROUP BY date"
	         //"SELECT eid,e_content,selectMeal,date_format(createDate,'%Y%m%d') AS createDate,sum(kcal) AS kcal FROM Eatingplandata WHERE user_username=123 GROUP BY createDate"
	   );
	   
	   List resultList = query.getResultList();
	   List r1=new ArrayList();
	   List r2=new ArrayList();
	   System.out.println(resultList);
	      
	      for(Object data : resultList) {
	         Object[] result = (Object[])data;
	         r1.add(result[0]);
	         r2.add(result[1]);
	         
	      }
	      
	      
	   model.addAttribute("chart1",r1);
	   model.addAttribute("chart2",r2);
	   return "eating/chart";
	   
	}

@PostMapping("/insert")
@ResponseBody
public ResponseEntity<EatingPlanData> postEating(Principal principal, @RequestBody EatingPlanData eatingPlanData){
	System.out.println("post request");
	System.out.println(eatingPlanData.toString());
	User user = userRepository.findByUsername(principal.getName());
	eatingPlanData.setUser(user);
	eatingPlanRepository.save(eatingPlanData);
	return new ResponseEntity<EatingPlanData>(eatingPlanData, HttpStatus.CREATED);
	
}

@GetMapping("/list")
public String getList(Model model,
		@PageableDefault(size = Integer.MAX_VALUE, sort = "eid", direction = Sort.Direction.DESC) Pageable pageable) {

	model.addAttribute("boardList", eatingService.findEatingList(pageable));

	return "eating/list";
}


}