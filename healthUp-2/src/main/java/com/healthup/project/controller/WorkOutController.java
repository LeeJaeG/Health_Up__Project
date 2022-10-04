package com.healthup.project.controller;

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
import com.healthup.project.model.SleepData;
import com.healthup.project.model.User;
import com.healthup.project.model.WorkOutData;
import com.healthup.project.repository.SleepDataRepository;
import com.healthup.project.repository.UserRepository;
import com.healthup.project.repository.WorkOutDataRepository;
import com.healthup.project.service.SleepService;
import com.healthup.project.service.WorkOutService;

@Controller
@RequestMapping("/workout")
public class WorkOutController {
	@Autowired
	WorkOutDataRepository workOutDataRepository;

	@Autowired
	WorkOutService workOutService;
	@Autowired
	EntityManager em;

	@GetMapping("/form")
	public String eatForm() {

		return "sleep/sleepForm";

	} 
	
	@GetMapping("/chart")
	public String eatChart(Model model,@PageableDefault(sort = "wid", direction = Sort.Direction.DESC) Pageable pageable,HttpSession session) {
		String email = (String) session.getAttribute("userId");
		String que = "SELECT wo_kg AS kg,date_format(createDate,'%Y%m%d') AS date FROM WorkOutData WHERE email="+"'"+email+"' GROUP BY date ORDER BY createDate ASC";
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
		System.out.println(r1);
		System.out.println(r2);
		
		model.addAttribute("chart1", r1);
		model.addAttribute("chart2", r2);
		model.addAttribute("workOutList", workOutService.findWorkOutList(pageable));
		return "workout/chart";

	}

	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<WorkOutData> postEating(HttpSession session, @RequestBody WorkOutData workOutData) {
		System.out.println("post request");
		System.out.println(workOutData.toString());
		String email = (String) session.getAttribute("userId");
		workOutData.setEmail(email);
		workOutDataRepository.save(workOutData);
		return new ResponseEntity<WorkOutData>(workOutData, HttpStatus.CREATED);

	}

	@GetMapping("/youtube")
	public String get() {
		return "workout/data";
	}

}
