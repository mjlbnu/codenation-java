package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Company;

// Classe responsável pela persistência
public interface CompanyRepository extends CrudRepository<Company, Long>{
	
	@Query(value = "select distinct co.* from COMPANY co " + 
			"INNER JOIN CANDIDATE ca " + 
			"ON co.id = ca.company_id " + 
			"INNER JOIN ACCELERATION ac " + 
			"ON ca.acceleration_id = ac.id " +
			"and ac.id = :accelerationId", nativeQuery = true)
	List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);
	
	@Query(value = "select co.* from COMPANY co " + 
			"INNER JOIN CANDIDATE ca " + 
			"ON co.id = ca.company_id " + 
			"and ca.user_id = :userId", nativeQuery = true)
	List<Company> findByUserId(@Param("userId") Long userId);

}
