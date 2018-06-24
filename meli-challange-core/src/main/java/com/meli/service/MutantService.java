package com.meli.service;

import com.meli.exception.BusinessExeption;
import com.meli.model.Mutant;

public interface MutantService {
	public Boolean isMutant(Mutant mutant) throws BusinessExeption;

	public void save(String dna, Boolean esMutante) throws BusinessExeption;

	public Integer obtenerCantidadMutantes() throws BusinessExeption;

	public Integer obtenerCantidadHumanos() throws BusinessExeption;

}
