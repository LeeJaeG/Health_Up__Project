package com.healthup.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.healthup.project.model.Board;
import com.healthup.project.repository.BoardRepository;



@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository=boardRepository;
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Board findBoardByName(Long id) {
		return boardRepository.findById(id).orElse(null);
	}
	public Board findBoardById(Long id) {
		return boardRepository.findById(id).orElse(new Board());
	}

	public Page<Board> findBoardList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return boardRepository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

}
