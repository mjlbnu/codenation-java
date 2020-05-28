package com.challenge.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;

// Classe de serviço
@Service
public class SubmissionServiceImpl implements SubmissionServiceInterface{
	
	@Autowired
	SubmissionRepository submissionRepository;

	@Override
	public Submission save(Submission object) {
		return submissionRepository.save(object);
	}

	@Override
	public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
		return submissionRepository.findHigherScoreByChallengeId(challengeId);
	}

	@Override
	public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
		return submissionRepository.findByChallenge_IdAndAcceleration_Id(challengeId, accelerationId);
	}

}
