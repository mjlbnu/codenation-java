package com.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;

// Classe de serviço
@Service
public class AccelerationServiceImpl implements AccelerationServiceInterface {
	
	// Injeção da classe responsável pela manipulação de dados
	@Autowired
	private AccelerationRepository accelerationRepository;

	// Método da interface ServiceInterface
	@Override
	public Acceleration save(Acceleration object) {
		return this.accelerationRepository.save(object);
	}

	// Métodos da interface AccelerationServiceInterface
	@Override
	public Optional<Acceleration> findById(Long id) {
		return this.accelerationRepository.findById(id);
	}

	@Override
	public List<Acceleration> findByCompanyId(Long companyId) {
		return this.accelerationRepository.findByCompanyId(companyId);
	}
}
