package com.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.bean.Request;
import com.fms.service.RequestService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("http://localhost:3000/")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	
	@PostMapping("/cardRequest/{userId}")
    public Request addCardRequest(@PathVariable int userId, @RequestBody Request request) {
        return requestService.save(request, userId);
    }
	
	@PutMapping("/approve/{requestId}")
    public Request approveRequest(@PathVariable Integer requestId) {
        return requestService.approveRequest(requestId);
    }
 
    @PutMapping("/reject/{requestId}")
    public Request rejectRequest(@PathVariable Integer requestId) {
        return requestService.rejectRequest(requestId);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Request>> getAllPendingRequest() {
        List<Request> req = requestService.getAllRequest();
        return ResponseEntity.ok(req);
    }
    
    
}

