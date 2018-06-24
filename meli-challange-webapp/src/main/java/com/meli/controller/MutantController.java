package com.meli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.meli.dto.Stats;
import com.meli.exception.BusinessExeption;
import com.meli.model.Mutant;
import com.meli.service.MutantService;

@RestController
@RequestMapping(value = "/meli")
public class MutantController {

	@Autowired
	MutantService mutantService;

	@RequestMapping(value = "/mutant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			"application/json" })
	@ResponseBody
	public ResponseEntity<Mutant> mutant(@RequestBody Mutant mutant, BindingResult result, ModelMap mode) {
		ResponseEntity<Mutant> res = null;
		try {
			Boolean esMutante = mutantService.isMutant(mutant);
			if (esMutante) {
				res = new ResponseEntity<>(HttpStatus.ACCEPTED);
			} else {
				res = new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			String json = new Gson().toJson(mutant);
			mutantService.save(json, esMutante);
		} catch (BusinessExeption be) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView stats(HttpServletRequest request, HttpServletResponse response, ModelMap mode) {
		ModelAndView model = new ModelAndView("stats");
		String mensaje = "";
		try {
			Integer nMutantes = mutantService.obtenerCantidadMutantes();
			Integer nHumanos = mutantService.obtenerCantidadHumanos();

			Stats s = new Stats(nMutantes, nHumanos);
			mensaje = new Gson().toJson(s);
		} catch (BusinessExeption be) {
			mensaje = be.getMessage();
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		model.addObject("mensaje", mensaje);
		return model;
	}

}
