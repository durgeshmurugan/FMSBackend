package com.fms.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.bean.EMICards;
import com.fms.bean.User;
import com.fms.dao.EMICardsDAO;
import com.fms.dao.UserDAO;

@Service
@Transactional
public class EMICardsServiceImpl implements EMICardsService {

	@Autowired
	private EMICardsDAO emiDAO;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public EMICards findById(Integer id) {
		return emiDAO.findById(id);
	}
	
	@Override
	public EMICards generateCard(EMICards card, Integer userId) {
        User user = userDAO.findById(userId);
        
        card.setBalance(card.getLimit());
        if(user == null) {
        	throw new RuntimeException("User not found");
        }    
        card.setUser(user);
        
        return emiDAO.save(card);
 
    }

	@Override
	public EMICards save(EMICards emiCard) {
		return emiDAO.save(emiCard);
	}

	@Override
	public void deleteById(Integer id) {
		EMICards emiCard = emiDAO.findById(id);
		if (emiCard != null) {
			emiDAO.delete(emiCard);
		}
	}

	@Override
	public EMICards findByUserId(Integer userId) {
		return emiDAO.findByUserId(userId);
	}

}
