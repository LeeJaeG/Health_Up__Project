package com.healthup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthup.project.model.WorkOutData;


@Repository
public interface WorkOutDataRepository extends JpaRepository<WorkOutData, Long> {

}
