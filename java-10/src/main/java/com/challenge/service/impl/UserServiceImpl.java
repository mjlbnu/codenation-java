package com.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.UserServiceInterface;

// Classe de serviço
@Service
public class UserServiceImpl implements UserServiceInterface {

	// Injeção da classe responsável pela manipulação de dados
	@Autowired
	private UserRepository userRepository;
	
	// Método da interface ServiceInterface
	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	// Métodos da interface UserServiceInterface
	@Override
	public Optional<User> findById(Long userId) {
		return this.userRepository.findById(userId);
	}

	@Override
	public List<User> findByAccelerationName(String name) {
		return this.userRepository.findByAccelerationName(name);
	}

	@Override
	public List<User> findByCompanyId(Long companyId) {
		return this.userRepository.findByCompanyId(companyId);
	}

}
