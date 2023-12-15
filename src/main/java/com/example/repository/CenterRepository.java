package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Center;
import com.example.model.Test;
import com.example.model.User;
@Repository
public interface CenterRepository extends JpaRepository<Center, Integer>{
	Center findByCentername(String centername);
//	List<Center> getCenterById(int id);

	  List<Center> findCentersByTestId(Integer testId);


}
