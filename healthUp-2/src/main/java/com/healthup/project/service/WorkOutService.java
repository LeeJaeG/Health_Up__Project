package com.healthup.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.healthup.project.model.WorkOutData;
import com.healthup.project.repository.WorkOutDataRepository;




@Service
public class WorkOutService {
	
	private final WorkOutDataRepository Repository;
	
	public WorkOutService(WorkOutDataRepository Repository) {
		this.Repository=Repository;
	}
	
	public void save(WorkOutData sleep) {
		Repository.save(sleep);
	}
	
	public WorkOutData findEatingByName(Long id) {
		return Repository.findById(id).orElse(null);
	}
	public WorkOutData findEatingById(Long id) {
		return Repository.findById(id).orElse(new WorkOutData());
	}

	public Page<WorkOutData> findWorkOutList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return Repository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		Repository.deleteById(id);
	}

}
