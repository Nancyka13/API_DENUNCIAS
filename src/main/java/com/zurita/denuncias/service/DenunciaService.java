package com.zurita.denuncias.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.zurita.denuncias.entity.Denuncia;


public interface DenunciaService {
	public List<Denuncia>findAll(Pageable page);
	public List<Denuncia> findByTitulo(String titulo, Pageable page);
	public Denuncia findById(int id);
	public Denuncia save(Denuncia empleado);
	public Denuncia update(Denuncia empleado);
	public void delete(int id);
}
