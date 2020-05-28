package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Acceleration;

// Responsável pela persistência
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {
	
	@Query(value = "select ac.* from ACCELERATION ac " +
			"INNER JOIN CANDIDATE ca " + 
			"ON ac.id = ca.acceleration_id " + 
			"INNER JOIN COMPANY co " + 
			"ON ca.company_id = co.id " + 
			"where co.id = :companyId", nativeQuery = true)
	List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

}
