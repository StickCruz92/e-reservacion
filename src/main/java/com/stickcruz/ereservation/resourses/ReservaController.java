/**
 * 
 */
package com.stickcruz.ereservation.resourses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stickcruz.ereservation.model.Cliente;
import com.stickcruz.ereservation.model.Reserva;
import com.stickcruz.ereservation.resourses.vo.ReservaVO;
import com.stickcruz.ereservation.services.ClienteService;
import com.stickcruz.ereservation.services.ReservaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/reserva")
@Api(tags = "reserva")
public class ReservaController {

	private final ReservaService reservaService;
	private final ClienteService clienteService;
	
	public ReservaController (ReservaService reservaService, ClienteService clienteService) {
		this.reservaService = reservaService;
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ApiOperation(value = "Crear Reserva", notes = "Servicio para crear un nueva reserva")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reserva creada correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida") })
	public ResponseEntity<Reserva> createReserva(@RequestBody ReservaVO reservaVO){
		Cliente cliente = clienteService.findByIdentificacion(reservaVO.getCliente().getIdentificacion());
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setIdReserva(reservaVO.getIdReserva());
		reserva.setFechaIngreso(reservaVO.getFechaIngreso());
		reserva.setFechaSalida(reservaVO.getFechaSalida());
		reserva.setCantidadPersonas(reservaVO.getCantidadPersonas());
		reserva.setDescripcion(reservaVO.getDescripcion());
		
		return new ResponseEntity<>(this.reservaService.create(reserva), HttpStatus.CREATED);
	}
	
	@PutMapping("/{codigoReserva}")
	@ApiOperation(value = "Actualizar Reserva", notes = "Servicio para actualizar una reserva")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reserva actualizada correctamente"),
			@ApiResponse(code = 404, message = "Reserva no encontrada") })
	public ResponseEntity<Reserva> updateReserva(@PathVariable("codigoReserva") String codigoReserva,
			ReservaVO reservaVO) {

		Reserva reserva = this.reservaService.findByCodigoRes(codigoReserva);
		if (reserva == null) {
			return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
		} else {
			Cliente cliente = clienteService.findByIdentificacion(reservaVO.getCliente().getIdentificacion());
			reserva.setCliente(cliente);
			reserva.setFechaIngreso(reservaVO.getFechaIngreso());
			reserva.setFechaSalida(reservaVO.getFechaSalida());
			reserva.setCantidadPersonas(reservaVO.getCantidadPersonas());
			reserva.setDescripcion(reservaVO.getDescripcion());
		}
		return new ResponseEntity<>(this.reservaService.update(reserva), HttpStatus.OK);
	}

	@DeleteMapping("/{codigoReserva}")
	@ApiOperation(value = "Eliminar Reserva", notes = "Servicio para eliminar una reserva")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reserva eliminada correctamente"),
			@ApiResponse(code = 404, message = "Reserva no encontrada") })
	public void removeCliente(@PathVariable("codigoReserva") String codigoReserva) {
		Reserva reserva = this.reservaService.findByCodigoRes(codigoReserva);
		if (reserva != null) {
			this.reservaService.delete(reserva);
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Reservas", notes = "Servicio para listar todas las reservas")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reservas encontrados"),
			@ApiResponse(code = 404, message = "Reservas no encontrados") })
	public ResponseEntity<List<Reserva>> findAll() {
		return ResponseEntity.ok(this.reservaService.findAll());
	}
}