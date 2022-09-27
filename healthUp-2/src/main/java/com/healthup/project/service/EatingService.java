package com.healthup.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.healthup.project.model.EatingPlanData;
import com.healthup.project.repository.EatingPlanRepository;



@Service
public class EatingService {
	
	private final EatingPlanRepository eatingRepository;
	
	public EatingService(EatingPlanRepository eatingRepository) {
		this.eatingRepository=eatingRepository;
	}
	
	public void save(EatingPlanData eating) {
		eatingRepository.save(eating);
	}
	
	public EatingPlanData findEatingByName(Long id) {
		return eatingRepository.findById(id).orElse(null);
	}
	public EatingPlanData findEatingById(Long id) {
		return eatingRepository.findById(id).orElse(new EatingPlanData());
	}

	public Page<EatingPlanData> findEatingList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return eatingRepository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		eatingRepository.deleteById(id);
	}

}
