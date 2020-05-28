package com.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import com.challenge.service.interfaces.CompanyServiceInterface;

// Classe de serviço
@Service
public class CompanyServiceImpl implements CompanyServiceInterface{
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company save(Company object) {
		return companyRepository.save(object);
	}

	@Override
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Override
	public List<Company> findByAccelerationId(Long accelerationId) {
		return companyRepository.findByAccelerationId(accelerationId);
	}

	@Override
	public List<Company> findByUserId(Long userId) {
		return companyRepository.findByUserId(userId);
	}

}
