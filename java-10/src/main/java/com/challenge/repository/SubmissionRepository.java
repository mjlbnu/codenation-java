package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;

// Classe responsável pela persistência
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId>{
	
	@Query(value = "select nvl(max(score),0) from SUBMISSION sub " + 
			"where sub.challenge_id = :challengeId", nativeQuery = true)
	BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);
	
	@Query(value = "select sub.* from SUBMISSION sub " + 
			"INNER JOIN ACCELERATION ac " + 
			"ON sub.challenge_id = ac.challenge_id " + 
			"where sub.challenge_id = :challengeId " + 
			"and ac.id = :accelerationId", nativeQuery = true)
	List<Submission> findByChallenge_IdAndAcceleration_Id(@Param("challengeId") Long challengeId,
			@Param("accelerationId") Long accelerationId);
	

}
