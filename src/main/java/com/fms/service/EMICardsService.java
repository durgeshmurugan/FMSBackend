package com.fms.service;

import com.fms.bean.EMICards;

public interface EMICardsService {
	
	EMICards findById(Integer id);
	 
    EMICards save(EMICards emiCard);
 
    void deleteById(Integer id);

	EMICards generateCard(EMICards card, Integer userId);
	
	EMICards findByUserId(Integer userId);

}
