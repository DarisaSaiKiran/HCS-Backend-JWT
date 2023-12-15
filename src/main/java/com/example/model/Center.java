package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Center {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String centername;
	private String contact;
	private String city;
	private String address;
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	@EqualsAndHashCode.Exclude 
//	@ToString.Exclude
	@JsonIgnore
	  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "center_tests",
	        joinColumns = { @JoinColumn(name = "center_id") },
	        inverseJoinColumns = { @JoinColumn(name = "test_id") })
	  private Set<Test> test = new HashSet<>();
	
	
	 public void addTest(Test test) {
		    this.test.add(test);
		    test.getCenters().add(this);
		  }
	 public void removeTest(int testId) {
		    Test tag = this.test.stream().filter(t -> t.getId() == testId).findFirst().orElse(null);
		    if (tag != null) {
		      this.test.remove(tag);
		      tag.getCenters().remove(this);
		    }
		  }
}
