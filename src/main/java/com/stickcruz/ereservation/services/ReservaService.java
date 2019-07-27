/**
 * 
 */
package com.stickcruz.ereservation.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stickcruz.ereservation.model.Cliente;
import com.stickcruz.ereservation.model.Reserva;
import com.stickcruz.ereservation.repository.ReservaRepository;

/**
 * @author stick
 *
 */
@Service
@Transactional(readOnly = true)
public class ReservaService {

	private final ReservaRepository reservaRepository;
	
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}
	
	/**
	 * Método para realizar la operación de guardar una reserva
	 * 
	 * @param reserva
	 * @return
	 */
	@Transactional
	public Reserva create(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}

	/**
	 * Método para realizar la operación de actualizar una reserva
	 * 
	 * @param reserva
	 * @return
	 */
	@Transactional
	public Reserva update(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}

	/**
	 * Método para realizar la operación de eliminar una reserva
	 * 
	 * @param reserva
	 */
	@Transactional
	public void delete(Reserva reserva) {
		this.reservaRepository.delete(reserva);
	}

	
	/**
	 * Método para consultar todos las reservas
	 * @return
	 */
	public List<Reserva> findAll(){
		return this.reservaRepository.findAll();
	}
	
	/**
	 * Método para consultar las reservas por un cliente en específico
	 * 
	 * @param cliente
	 * @return
	 */
	public List<Reserva> findByCliente(Cliente cliente){
		return this.reservaRepository.findByCliente(cliente);
	}
	
	/**
	 * Método para consultar las reservas por la fecha de ingreso en
	 * base a un rango de fechas
	 * 
	 * @param fechaInicio
	 * @param fechaSalida
	 * @return
	 */
	public List<Reserva> find(Date fechaInicio, Date fechaSalida){
		return this.reservaRepository.find(fechaInicio, fechaSalida);
	}

	/**
	 * Método para consultar una reserva por su código de reserva
	 * @param IdReserva
	 * @return
	 */
	public Reserva findByCodigoRes(String IdReserva) {
		return this.reservaRepository.findByIdReserva(IdReserva);
	}
}
