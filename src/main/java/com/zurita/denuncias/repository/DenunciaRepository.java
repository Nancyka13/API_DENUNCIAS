package com.zurita.denuncias.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zurita.denuncias.entity.Denuncia;


@Repository

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
	List<Denuncia> findByTituloContaining(String texto, Pageable page);

	Denuncia findByTitulo(String texto);

	
}


