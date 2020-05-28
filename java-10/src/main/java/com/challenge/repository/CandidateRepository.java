package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

// Classe Responsável pela persistência
public interface CandidateRepository extends CrudRepository<Candidate, CandidateId>{

	@Query(value = "select ca.* from CANDIDATE ca " + 
			"where ca.user_id = :userId " + 
			"and ca.acceleration_id = :accelerationId " + 
			"and ca.company_id = :companyId", nativeQuery = true)
	Optional<Candidate> findByIds(@Param("userId") Long userId, 
			@Param("accelerationId") Long companyId,
			@Param("companyId") Long accelerationId);
	
	@Query(value = "select ca.* from CANDIDATE ca " + 
			"where ca.company_id = :companyId", nativeQuery = true)
	List<Candidate> findByCompanyId(@Param("companyId") Long companyId);
	
	@Query(value = "select ca.* from CANDIDATE ca " + 
			"where ca.acceleration_id = :accelerationId", nativeQuery = true)
	List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);

}
