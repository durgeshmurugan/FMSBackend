package com.fms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.bean.Request;
import com.fms.dao.RequestDAO;
import com.fms.dao.UserDAO;

@Service
@Transactional
public class RequestService {

	@Autowired
    private RequestDAO requestDAO;
	
	@Autowired
	private UserDAO userDAO;
 
	public Request save(Request request, Integer userId) {
        request.setUser(userDAO.findById(userId)); // Assuming you have a method to find user by ID
        request.setStatus("pending");
        return requestDAO.save(request);
    }
    
	public boolean update(Request request) {
		return requestDAO.update(request);
	}
	
	public Request approveRequest(Integer requestId) {
        Request request = requestDAO.findById(requestId);
        if (request != null) {
            request.setStatus("APPROVED");
            requestDAO.update(request);
        }
        return request;
    }
 
    public Request rejectRequest(Integer requestId) {
        Request request = requestDAO.findById(requestId);
        if (request != null) {
            request.setStatus("REJECTED");
            requestDAO.update(request);
        }
        return request;
    }
    
    public List<Request> getAllRequest(){
    	return requestDAO.findAll();
    }
    
}
