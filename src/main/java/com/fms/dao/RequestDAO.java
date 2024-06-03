package com.fms.dao;

import java.util.List;

import com.fms.bean.Request;

public interface RequestDAO {
	
	Request save(Request request);
	
	boolean update(Request request);
	
    Request findById(Integer requestId);
    
    List<Request> findAll();

    Request findByUserId(Integer userId);
}
