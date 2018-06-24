package com.meli.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.meli.exception.BusinessExeption;
import com.meli.model.Mutant;
import com.meli.service.MutantService;

@Service
public class MutantServiceImpl implements MutantService {

	@Override
	public Boolean isMutant(Mutant mutant) {
		String[][] mat = llenarMatriz(mutant.getDna());
		int c = 0;
		int secHor = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length - 1; j++) {
				if (c < 4 && mat[i][j].equals(mat[i][j + 1])) {
					c++;
					if (c == 3) {
						secHor++;
					}
				} else {
					c = 0;
				}
			}
		}

		int secVer = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length - 1; j++) {
				if (c < 4 && mat[j][i].equals(mat[j + 1][i])) {
					c++;
					if (c == 3) {
						secVer++;
					}
				} else {
					c = 0;
				}
			}
		}

		int secDiagPrin = 0;
		for (int i = 0; i < mat.length - 1; i++) {
			if (c < 4 && mat[i][i].equals(mat[i + 1][i + 1])) {
				c++;
				if (c == 3) {
					secDiagPrin++;
				}
			} else {
				c = 0;
			}
		}

		int secDiagSecu = 0;
		int i = 0;
		for (int j = mat.length - 1; j >= 0; j--) {
			if (i < mat.length-1) {
				if (c < 4 && mat[i][j].equals(mat[i + 1][j - 1])) {
					c++;
					if (c == 3) {
						secDiagSecu++;
					}
				} else {
					c = 0;
				}
				i++;
			}
		}

		int total = secHor + secVer + secDiagPrin + secDiagSecu;
		return total > 1;
	}

	private String[][] llenarMatriz(List<String> dna) {
		String[][] mat = new String[dna.size()][dna.get(0).length()];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				mat[i][j] = dna.get(i).charAt(j) + "";
			}
		}
		return mat;
	}

	@Override
	public void save(String dna, Boolean esMutante) {
		Entity entidad = new Entity("Mutant");
		entidad.setProperty("dna", dna);
		entidad.setProperty("esMutante", esMutante);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put(entidad);
	}

	@Override
	public Integer obtenerCantidadMutantes() throws BusinessExeption {
		Query q = new Query("Mutant");
		q.setFilter(Query.FilterOperator.EQUAL.of("esMutante", true));

		return DatastoreServiceFactory.getDatastoreService().prepare(q).asList(FetchOptions.Builder.withDefaults())
				.size();
	}

	@Override
	public Integer obtenerCantidadHumanos() throws BusinessExeption {
		Query q = new Query("Mutant");
		q.setFilter(Query.FilterOperator.EQUAL.of("esMutante", false));

		return DatastoreServiceFactory.getDatastoreService().prepare(q).asList(FetchOptions.Builder.withDefaults())
				.size();
	}

}
