package com.healthup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthup.project.model.SleepData;


@Repository
public interface SleepDataRepository extends JpaRepository<SleepData, Long> {

}
