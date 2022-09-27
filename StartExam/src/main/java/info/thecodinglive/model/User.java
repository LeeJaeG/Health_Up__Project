package info.thecodinglive.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table
public class User {


private String name;
private int age;
private String tel;
private int plankcal;
private int datacount;

@Id
private String username;
private String password;
@Column(name="role")
@Enumerated(EnumType.ORDINAL)
private Role role;
@CreationTimestamp
private Timestamp createDate;
}
