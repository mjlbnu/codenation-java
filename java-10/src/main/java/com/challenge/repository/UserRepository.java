package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.User;

// Responsável pela persistência
public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query(value = "select user.* from USERS user " +
			"INNER JOIN CANDIDATE ca " + 
			"ON user.id = ca.user_id " + 
			"INNER JOIN ACCELERATION ac " + 
			"ON ca.acceleration_id = ac.id " + 
			"WHERE ac.name = :accelerationName", nativeQuery = true)
	List<User> findByAccelerationName(@Param("accelerationName") String name);
	
	@Query(value = "select user.* from USERS user " + 
			"INNER JOIN CANDIDATE ca " + 
			"ON user.id = ca.user_id " + 
			"WHERE ca.company_id = :companyId", nativeQuery = true)
	List<User> findByCompanyId(@Param("companyId") Long companyId);

}
