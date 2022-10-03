package com.healthup.project.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.healthup.project.model.EatingPlanData;
import com.healthup.project.model.User;
import com.healthup.project.repository.EatingPlanRepository;
import com.healthup.project.repository.UserRepository;
import com.healthup.project.service.EatingService;

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

		return "eating";

	}

	@GetMapping("/chart")
	public String eatChart(HttpSession session,Model model,@PageableDefault(size = Integer.MAX_VALUE, sort = "eid", direction = Sort.Direction.DESC) Pageable pageable) {

		String email = (String) session.getAttribute("userId");
		String que = "select date_format(createDate,'%Y%m%d') AS date,sum(kcal) AS kcal from EatingPlanData WHERE email='"+email+"' GROUP BY date ORDER BY date ASC";
		
		
		Query query = em.createQuery(
				que
		// "SELECT eid,e_content,selectMeal,date_format(createDate,'%Y%m%d') AS
		// createDate,sum(kcal) AS kcal FROM Eatingplandata WHERE user_username=123
		// GROUP BY createDate"
		);

		List resultList = query.getResultList();
		List r1 = new ArrayList();
		List r2 = new ArrayList();
		System.out.println(resultList);

		for (Object data : resultList) {
			Object[] result = (Object[]) data;
			r1.add(result[0]);
			r2.add(result[1]);

		}

		model.addAttribute("date", r1);
		model.addAttribute("kcal", r2);
		model.addAttribute("eatingList", eatingService.findEatingList(pageable));
		return "eating/chart";

	}

//	@GetMapping("/list")
//	public String getList(Model model,
//			@PageableDefault(size = Integer.MAX_VALUE, sort = "eid", direction = Sort.Direction.DESC) Pageable pageable) {
//
//		model.addAttribute("eatingList", eatingService.findEatingList(pageable));
//
//		return "eating/list";
//	}
	
	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<EatingPlanData> postEating(HttpSession session, @RequestBody EatingPlanData eatingPlanData) {
		System.out.println("post request");
		System.out.println(eatingPlanData.toString());
		String email = (String) session.getAttribute("userId");
		eatingPlanData.setEmail(email);
		eatingPlanRepository.save(eatingPlanData);
		
		return new ResponseEntity<EatingPlanData>(eatingPlanData, HttpStatus.CREATED);

	}

}
