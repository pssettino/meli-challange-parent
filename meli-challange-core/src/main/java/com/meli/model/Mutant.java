package com.meli.model;

import java.io.Serializable;
import java.util.List;

public class Mutant implements Serializable {
	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

}
