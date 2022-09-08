package info.thecodinglive.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
public class EatingPlanData implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long eid;
private String selectMeal; //간식
private int kcal;
private String e_content;

@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)//
@JsonIgnore
private User user;

@CreationTimestamp
@DateTimeFormat
private Date createDate;
//추가기능 : 수분량 기준치 2L

//탄 4kcal 지방 9kcal 단백질 4kcal

//bmi 기준 비만 칼로리 적게 적정 유지 저체중 칼로리보다 높게!

//

}


