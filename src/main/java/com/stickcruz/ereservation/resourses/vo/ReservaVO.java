/**
 * 
 */
package com.stickcruz.ereservation.resourses.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author stick
 *
 */
@Data
public class ReservaVO {

	private String idReserva;
	private Date fechaIngreso;
	private Date fechaSalida;
	private int cantidadPersonas;
	private String descripcion;
	private ClienteVO cliente;
}
