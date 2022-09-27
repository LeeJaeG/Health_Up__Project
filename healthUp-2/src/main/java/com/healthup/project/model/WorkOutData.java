package com.healthup.project.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class WorkOutData {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long w_id;
private int wo_kg;
private String wd_title;
private String wd_content;
//운동시간 저장
@CreationTimestamp
private Timestamp createDate;

@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)//
@JsonIgnore
private User user;
}
