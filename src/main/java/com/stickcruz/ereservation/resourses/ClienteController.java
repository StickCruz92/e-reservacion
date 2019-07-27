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
import com.stickcruz.ereservation.resourses.vo.ClienteVO;
import com.stickcruz.ereservation.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Servicio web de cliente
 * 
 * @author stick
 *
 */
@RestController
@RequestMapping("/api/cliente")
@Api(tags = "cliente")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválidad")})
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clientevo) {
		Cliente cliente = new Cliente();
		cliente.setNombre(clientevo.getNombre());
		cliente.setApellido(clientevo.getApellido());
		cliente.setDireccion(clientevo.getDireccion());
		cliente.setTelefono(clientevo.getTelefono());
		cliente.setEmail(clientevo.getEmail());
		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}

	@PutMapping("/{identificacion}")
	@ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<Cliente> updateCliente(@PathVariable("identificacion") String identificacion,
			ClienteVO clientevo) {

		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);

		if (cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		} else {
			cliente.setNombre(clientevo.getNombre());
			cliente.setApellido(clientevo.getApellido());
			cliente.setDireccion(clientevo.getDireccion());
			cliente.setTelefono(clientevo.getTelefono());
			cliente.setEmail(clientevo.getEmail());
			return new ResponseEntity<>(this.clienteService.update(cliente), HttpStatus.OK);
		}

	}

	@DeleteMapping("/{identificacion}")
	@ApiOperation(value = "Eliminar Cliente", notes = "Servicio para eliminar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void RemoveCliente(@PathVariable("identificacion") String identificacion) {

		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		if (cliente != null) {
			this.clienteService.delete(cliente);
		}

	}
	
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Servicio para listar todos los clientes")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente encontrados"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<List<Cliente>> findAll () {
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
