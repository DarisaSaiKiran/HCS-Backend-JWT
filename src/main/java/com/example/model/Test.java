package com.example.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table
@Data
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
//	@NotBlank(message = "test name cannot be null")
	private String testName;
	@EqualsAndHashCode.Exclude // <<<<<<<<<<
	@ToString.Exclude
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "test")
		  @JsonIgnore
//	 @JsonInclude(JsonInclude.Include.NON_EMPTY)

		  private Set<Center> centers = new HashSet<>();

	
}
