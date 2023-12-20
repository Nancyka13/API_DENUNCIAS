package com.zurita.denuncias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zurita.denuncias.entity.Denuncia;
import com.zurita.denuncias.repository.DenunciaRepository;
import com.zurita.denuncias.service.DenunciaService;


@Service

public class DenunciaServiceImpl implements DenunciaService{

	@Autowired
	private DenunciaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Denuncia> findAll(Pageable page) {
		try {

	        return repository.findAll(page).toList();
	    } catch (Exception e) {
	        return null;
	    }
	}

	@Override
	public List<Denuncia> findByTitulo(String titulo, Pageable page) {
		try {

	        return repository.findByTituloContaining(titulo, page);
	    } catch (Exception e) {
	        return null;
	    }
	}

	@Override
	public Denuncia findById(int id) {
		try {
			Denuncia registro = repository.findById(id).orElseThrow();
			return registro;
		
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Denuncia save(Denuncia denuncia) {
		try {
			//DenunciaValidator.save(denuncia);
			denuncia.setActivo(true);
			Denuncia registro=repository.save(denuncia);
			return registro;
		} catch (Exception e) {
			return null;
		}
	}


	
	@Override
	public Denuncia update(Denuncia denuncia) {
		try {
			//DenunciaValidator.save(denuncia);
			Denuncia registro = repository.findById(denuncia.getId()).orElseThrow();
			registro.setDni(denuncia.getDni());
			registro.setFecha(denuncia.getFecha());
			registro.setTitulo(denuncia.getTitulo());
			registro.setDireccion(denuncia.getDireccion());
			registro.setDescripcion(denuncia.getDescripcion());
			repository.save(registro);
			return registro;
		
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			Denuncia registro = repository.findById(id).orElseThrow();
			repository.delete(registro);

		} catch (Exception e) {
			
		}
		
	}

}
