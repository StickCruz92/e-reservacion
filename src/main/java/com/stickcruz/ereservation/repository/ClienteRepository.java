/**
 * 
 */
package com.stickcruz.ereservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stickcruz.ereservation.model.Cliente;

/**
 * Interface para definir las operacion de BD de cliente
 * @author stick
 *
 */
public interface ClienteRepository  extends JpaRepository<Cliente, String>{

	public List<Cliente> findByApellido(String apellido);
	
	public Cliente findByIdentificacion (String identificacion);

}
