/**
 * 
 */
package com.stickcruz.ereservation.resourses.vo;

import lombok.Data;

/** Clase que representa la clase cliente
 * @author stick
 *
 */
@Data
public class ClienteVO {
	
	private String idCliente;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
	
}
