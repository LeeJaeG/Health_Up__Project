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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class UserData {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long ud_id;
private int height;
private int weight;
private int bmr; //기초대사량
private double s_muscle;
private double body_fat;

@CreationTimestamp
@DateTimeFormat(pattern = "yyyy/MM/dd HH")
private Timestamp createDate;

@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)//
@JsonIgnore
private User user;

//치팅데이 넣기 날짜관련
//

}
