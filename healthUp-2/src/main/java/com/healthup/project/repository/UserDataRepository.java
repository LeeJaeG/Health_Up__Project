package com.healthup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthup.project.model.UserData;



@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
