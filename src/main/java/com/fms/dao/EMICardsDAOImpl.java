package com.fms.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.fms.bean.EMICards;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EMICardsDAOImpl implements EMICardsDAO {

	@Autowired
	private EntityManager em;

	@Override
	public EMICards findById(Integer userCardId) {
		return em.find(EMICards.class, userCardId);
	}

	@Override
	public EMICards save(EMICards emiCard) {
		if (emiCard.getUserCardId() == null) {
			em.persist(emiCard);
			return emiCard;
		} else {
			return em.merge(emiCard);
		}
	}

	@Override
	public void delete(EMICards emiCard) {
		em.remove(emiCard);
	}

	@Override
	public EMICards findByUserId(Integer userId) {

		TypedQuery<EMICards> query = em.createQuery("SELECT c FROM EMICards c WHERE c.user.userId = :userId",
				EMICards.class);
		query.setParameter("userId", userId);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
