package com.fms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.bean.EMICards;
import com.fms.service.EMICardsService;

@RestController
@CrossOrigin("http://localhost:3000/")
public class EMICardsController {

	@Autowired
	private EMICardsService emiService;

	@PostMapping("/cardGeneration/{userId}")
	public EMICards add(@PathVariable Integer userId, @RequestBody EMICards card) {
		return emiService.generateCard(card, userId);
	}

	@GetMapping("/getCard/{userCardId}")
	public EMICards getProducts(@PathVariable Integer userCardId) {
		return emiService.findById(userCardId);
	}

	@DeleteMapping("deleteCard/{userCardId}")
	public void delete(@PathVariable Integer userCardId) {
		emiService.deleteById(userCardId);
	}

	@GetMapping("/getCardByUserId/{userId}")
	public ResponseEntity<EMICards> getMethodName(@PathVariable Integer userId) {
		EMICards cardsdetails = emiService.findByUserId(userId);
		if(cardsdetails != null) {
			return ResponseEntity.ok(cardsdetails);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
