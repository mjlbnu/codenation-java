package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Challenge;

// Classe responsável pela persistência
public interface ChallengeRepository extends CrudRepository<Challenge, Long>{
	
	@Query(value = "SELECT * FROM challenge ch " +
            "JOIN ACCELERATION ac ON ac.challenge_id = ch.id " +
            "JOIN CANDIDATE ca ON ca.acceleration_id = ac.id " +
            "where ac.id = :accelerationId and ca.user_id = :userId", nativeQuery = true)
	List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,
			@Param("userId") Long userId);

}
