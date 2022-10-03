package com.healthup.project.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class SleepData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sid;

	private Timestamp toDate;
	
	private Timestamp fromDate;
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy/MM/dd HH/mm")
	private Date createDate;
	
	private String email;
}
