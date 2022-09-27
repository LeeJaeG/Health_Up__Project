package com.healthup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthup.project.model.Board;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
