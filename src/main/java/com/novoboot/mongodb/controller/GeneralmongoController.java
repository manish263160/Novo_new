package com.novoboot.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.mongodb.collection.CustomerReview;
import com.novoboot.mongodb.repository.FeedBackRepository;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping(value= "/permitall")
public class GeneralmongoController {

	@Autowired
	FeedBackRepository feedBackRepository;
	
	 @RequestMapping(value="/craeteFeedBack" ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseObject create(@RequestBody CustomerReview user) {
		 CustomerReview urmng = feedBackRepository.save(user);
	        return GenUtilities.getSuccessResponseObject(urmng, RESPONSE_CODES.SUCCESS.getDescription(),
					RESPONSE_CODES.SUCCESS.getCode());
	    }
	 
	   @RequestMapping(value = "/read/{id}") 
	    public ResponseObject read(@PathVariable String id) {
	        return GenUtilities.getSuccessResponseObject(feedBackRepository.findOne(id), RESPONSE_CODES.SUCCESS.getDescription(),
					RESPONSE_CODES.SUCCESS.getCode());
	    }

	    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseObject update(@RequestBody CustomerReview user) {
	    	CustomerReview usr= feedBackRepository.save(user);
	    	 return GenUtilities.getSuccessResponseObject(usr, RESPONSE_CODES.SUCCESS.getDescription(),
						RESPONSE_CODES.SUCCESS.getCode());
	    }

	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) 
	    public ResponseObject delete(@PathVariable String id) {
	        feedBackRepository.delete(id); 
	        return GenUtilities.getSuccessResponseObject( "Delete Done", RESPONSE_CODES.SUCCESS.getDescription(),
					RESPONSE_CODES.SUCCESS.getCode());
	    }
}
