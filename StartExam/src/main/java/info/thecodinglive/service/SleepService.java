package info.thecodinglive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.SleepData;
import info.thecodinglive.repository.BoardRepository;
import info.thecodinglive.repository.EatingPlanRepository;
import info.thecodinglive.repository.SleepDataRepository;

@Service
public class SleepService {
	
	private final SleepDataRepository sleepRepository;
	
	public SleepService(SleepDataRepository sleepRepository) {
		this.sleepRepository=sleepRepository;
	}
	
	public void save(SleepData sleep) {
		sleepRepository.save(sleep);
	}
	
	public SleepData findEatingByName(Long id) {
		return sleepRepository.findById(id).orElse(null);
	}
	public SleepData findEatingById(Long id) {
		return sleepRepository.findById(id).orElse(new SleepData());
	}

	public Page<SleepData> findSleepList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return sleepRepository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		sleepRepository.deleteById(id);
	}

}
