package com.zurita.denuncias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zurita.denuncias.converter.DenunciaConverter;
import com.zurita.denuncias.dto.DenunciaDTO;
import com.zurita.denuncias.entity.Denuncia;
import com.zurita.denuncias.service.DenunciaService;



@RestController
@RequestMapping("/denuncias/")

public class DenunciaController {

	@Autowired
	private DenunciaService service;

	@Autowired
	private DenunciaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<DenunciaDTO>> findAll(
	        @RequestParam(value = "titulo", required = false, defaultValue = "") String titulo,
	        @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
	        @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
	) {
	    Pageable page = PageRequest.of(pageNumber, pageSize);
	    List<Denuncia> denuncias;
	    if (titulo == null) {
	        denuncias = service.findAll(page);
	    } else {
	        denuncias = service.findByTitulo(titulo, page);
	    }
	    if (denuncias.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    List<DenunciaDTO> denunciasDTO = converter.fromEntity(denuncias);
		return ResponseEntity.ok(denunciasDTO);

	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> findById(@PathVariable("id") int id){
		Denuncia denuncia= service.findById(id);
		if(denuncia==null) {
			return ResponseEntity.notFound().build();
		}
		DenunciaDTO denunciaDTO=converter.fromEntity(denuncia);
		return ResponseEntity.ok(denunciaDTO);
	}
	
	@PostMapping
	public ResponseEntity<DenunciaDTO> create (@RequestBody DenunciaDTO denunciaDTO){
		Denuncia registro=service.save(converter.fromDTO(denunciaDTO));
		DenunciaDTO registroDTO=converter.fromEntity(registro);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
	}
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id,@RequestBody DenunciaDTO denunciaDTO){
		Denuncia registro = service.update(converter.fromDTO(denunciaDTO));
		if (registro==null) {
			return ResponseEntity.notFound().build();
		}
		DenunciaDTO registroDTO=converter.fromEntity(registro);
		return ResponseEntity.ok(registroDTO);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> delete (@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
	
}
