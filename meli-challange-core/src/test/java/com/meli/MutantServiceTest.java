package com.meli;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.meli.exception.BusinessExeption;
import com.meli.model.Mutant;
import com.meli.service.MutantService;
import com.meli.service.impl.MutantServiceImpl;

public class MutantServiceTest {

	MutantService mutantService = new MutantServiceImpl();

	@Test
	public void whenIsMutantIsSuccess() {
		List<String> dnaList = new ArrayList<>();
		dnaList.add("ATGCGA");
		dnaList.add("CAGTGC");
		dnaList.add("TTATGT");
		dnaList.add("AGAAGG");
		dnaList.add("CCCCTA");
		dnaList.add("TCACTG");
		Mutant m = new Mutant();
		m.setDna(dnaList);
		try {
			Boolean esMutante = mutantService.isMutant(m);
			assertTrue(esMutante);
		} catch (BusinessExeption e) {
			assertFalse(true);
		}

	}

	@Test
	public void whenIsMutantIsNotSuccess() {
		List<String> dnaList = new ArrayList<>();
		dnaList.add("ATGCGA");
		dnaList.add("CWGTWC");
		dnaList.add("TTATGT");
		dnaList.add("AGAAGG");
		dnaList.add("CWCCTA");
		dnaList.add("TCACTG");
		Mutant m = new Mutant();
		m.setDna(dnaList);
		try {
			Boolean esMutante = mutantService.isMutant(m);
			assertFalse(esMutante);
		} catch (BusinessExeption e) {
			assertFalse(true);
		}
	}

}
