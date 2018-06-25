package com.meli.dto;

import java.io.Serializable;

public class Stats implements Serializable {
	private Integer countMutantDna;
	private Integer countHumanDna;
	private Float ratio;

	public Stats(Integer countMutantDna, Integer countHumanDna) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = calcularRatio();
	}

	private Float calcularRatio() {
		Float valor = (new Float(this.countMutantDna) / new Float(this.countHumanDna));

		if (this.countHumanDna == 0) {
			return new Float(this.countMutantDna);
		} else {

			return valor.isNaN() ? new Float(0) : valor;
		}
	}

	public Integer getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(Integer countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public Integer getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(Integer countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}

}
