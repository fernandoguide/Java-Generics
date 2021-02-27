package com.devsuperior.demolazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.demolazy.util.Convertible;
import org.springframework.http.ResponseEntity;

public interface GenericService<T extends Convertible<DTO>, DTO, ID> {

	JpaRepository<T, ID> getRepository();
	
	default DTO findById(ID id) {
		Optional<T> result = getRepository().findById(id);
		return result.get().convert();
	}
	
	default List<DTO> findAll() {
		List<T> list = getRepository().findAll();
		return list.stream().map(Convertible::convert).collect(Collectors.toList());
	}
	default DTO save(T obj ) {
		return getRepository().save(obj).convert();
	}
	default void deleteByID(ID id) throws Exception {
		if(!getRepository().existsById(id)) {
			 throw  new Exception("Objeto nao encontrado!");
		}
		 getRepository().deleteById(id);
	}
}
