package com.fms.dao;

import com.fms.bean.EMICards;

public interface EMICardsDAO {

	EMICards findById(Integer id);

	EMICards save(EMICards emiCard);

	void delete(EMICards emiCard);
	
	EMICards findByUserId(Integer userId);
}
