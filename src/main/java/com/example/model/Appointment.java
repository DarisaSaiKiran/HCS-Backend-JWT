package com.example.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="appointment_id")
	private Integer id;
	private Date date;

	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "center_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
//	  @JsonIgnore
	  private Center center;

	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "test_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
//	  @JsonIgnore
	  private Test test;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	  @MapsId
//	  @JoinColumn(name = "userId")
//	  private User user;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
//	  @JsonIgnore
	  private User user;

	
	

}
