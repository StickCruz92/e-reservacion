package com.stickcruz.ereservation.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stickcruz.ereservation.model.Cliente;
import com.stickcruz.ereservation.repository.ClienteRepository;

/**
 * Clase para definir los servicios de cliente
 * @author stick
 *
 */
@Service
@Transactional(readOnly = true)
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService (ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente update (Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	@Transactional
	public void delete (Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	
	public Cliente findByIdentificacion (String identificacion) {
		return this.clienteRepository.findByIdentificacion(identificacion);
	}
	
	public List<Cliente> findByApellido(String apellido) {
		return this.clienteRepository.findByApellido(apellido);
	}
	
	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}
	
}
